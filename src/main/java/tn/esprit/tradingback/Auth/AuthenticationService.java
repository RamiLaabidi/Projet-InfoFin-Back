package tn.esprit.tradingback.Auth;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.tradingback.Entities.CompteBancaire;
import tn.esprit.tradingback.Entities.Enums.ROLE;
import tn.esprit.tradingback.Entities.PasswordResetToken;
import tn.esprit.tradingback.Entities.User;
import tn.esprit.tradingback.Repositories.CompteBancaireRepository;
import tn.esprit.tradingback.Repositories.PasswordResetTokenRepository;
import tn.esprit.tradingback.Repositories.UserRepository;
import tn.esprit.tradingback.Services.JwtService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository iUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final PasswordResetTokenRepository tokenRepository;
  private final JavaMailSender mailSender;

  @Autowired
  private CompteBancaireRepository iCompteBancaireRepository;
  @Transactional
  public AuthenticationResponse register(RegistreRequest request) {
    // Créer l'entité User (Utilisateur)
    User user = User.builder()
            .nom(request.getNom())
            .prenom(request.getPrenom())
            .numCin(request.getNumCin())
            .dateDeNaissance(request.getDateDeNaissance())
            .mail(request.getMail())
            .paysDeNaissance(request.getPaysDeNaissance())
            .nationalite(request.getNationalite())
            .adress(request.getAdress())
            .codePostal(request.getCodePostal())
            .contact(request.getContact())
            .motDePasse(passwordEncoder.encode(request.getMotDePasse())) // Mot de passe crypté
            .role(ROLE.JOUEUR) // Rôle par défaut
            .build();

    // Sauvegarder l'utilisateur pour générer son ID
    iUserRepository.save(user);

    // Créer le compte bancaire associé à l'utilisateur
    CompteBancaire compteBancaire = CompteBancaire.builder()
            .numCompte(request.getNumCompte()) // Numéro de compte saisi par l'utilisateur
            .nomBanque(request.getNomBanque()) // Nom de la banque saisi par l'utilisateur
            .dateOuverture(new Date()) // Date actuelle comme date d'ouverture
            .soldeCompte(1000.0f) // Solde initial à 1000$
            .user(user) // Associer le compte à l'utilisateur
            .build();

    // Sauvegarder le compte bancaire
    iCompteBancaireRepository.save(compteBancaire);

    // Associer le compte bancaire à l'utilisateur
    user.setCompteBancaire(compteBancaire);
    iUserRepository.save(user); // Sauvegarder l'utilisateur avec le compte bancaire

    // Générer un token JWT pour l'utilisateur
    String jwtToken = jwtService.generateToken(user, user.getUsername());

    // Retourner la réponse avec le token d'accès
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .build();
  }

  public AuthenticationResponse login(LoginRequest request) {
    // Validate input
    if (request.getMail() == null || request.getMail().isEmpty()) {
      throw new IllegalArgumentException("Email must not be empty");
    }
    if (request.getMotDePasse() == null || request.getMotDePasse().isEmpty()) {
      throw new IllegalArgumentException("Password must not be empty");
    }

    // Authenticate the user
    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getMail(),
                      request.getMotDePasse()
              )
      );
    } catch (Exception e) {
      throw new BadCredentialsException("Invalid credentials");
    }

    // Retrieve the user from the repository
    User user = iUserRepository.findByMail(request.getMail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    // Add custom claims for the token
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", user.getRole());
    claims.put("userId", user.getIdU());
    claims.put("photo", user.getPhoto());
    claims.put("name", user.getNom() + " " + user.getPrenom());

    // Generate JWT token with claims
    String jwtToken = jwtService.generateToken(user, user.getUsername());  // Without the claims


    // Return the response with the generated access token
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .build();
  }

  public void createPasswordResetTokenForUser(User user, String token) {
    PasswordResetToken myToken = new PasswordResetToken(token, user);
    tokenRepository.save(myToken);
  }

  public void sendEmail(String recipientAddress, String subject, String message) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(recipientAddress);
    email.setSubject(subject);
    email.setText(message);
    mailSender.send(email);
  }

  public Optional<User> findUserByMail(String mail) {
    return iUserRepository.findByMail(mail);
  }

  public void resetPassword(String token, String newPassword) {
    PasswordResetToken resetToken = tokenRepository.findByToken(token);
    if (resetToken == null || resetToken.isExpired()) {
      throw new IllegalArgumentException("Invalid or expired token");
    }

    User user = resetToken.getUser();
    user.setMotDePasse(passwordEncoder.encode(newPassword));
    iUserRepository.save(user);

    tokenRepository.delete(resetToken);
  }

}
