package tn.esprit.tradingback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.esprit.tradingback.DTO.UserUpdateDTO;
import tn.esprit.tradingback.Entities.CompteBancaire;
import tn.esprit.tradingback.Entities.Portefeuille;
import tn.esprit.tradingback.Entities.User;
import tn.esprit.tradingback.Repositories.PortefeuilleRepository;
import tn.esprit.tradingback.Repositories.UserRepository;
import tn.esprit.tradingback.Services.Interfaces.IUserService;


import java.util.Optional;

@Service
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortefeuilleRepository portefeuilleRepository;

    // Assign Portefeuille to User
    public User assignPortefeuilleToUser(Long idU, Portefeuille portefeuille) {
        User user = userRepository.findById(idU)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // You can create a new Portefeuille or use the existing one
        Portefeuille newPortefeuille = portefeuilleRepository.save(portefeuille);

        // Assign the portefeuille to the user
//        user.setPortefeuille(newPortefeuille);

        // Save the user with the updated portefeuille
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByMail(email);
    }

    public User updateUser(Long idU, UserUpdateDTO userUpdateDTO) {
        // Find the user by ID
        User user = userRepository.findById(idU)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update the fields
        user.setPhone(userUpdateDTO.getPhone());
        user.setLocation(userUpdateDTO.getLocation());
        user.setDescription(userUpdateDTO.getDescription());
        user.setAge(userUpdateDTO.getAge());
        user.setProfession(userUpdateDTO.getProfession());

        // Save the updated user back to the repository
        return userRepository.save(user);
    }
    public User updateUserByEmail(String email, UserUpdateDTO userUpdateDTO) {
        // Fetch the user by email
        User user = userRepository.findByMail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Update the fields from UserUpdateDTO
        if (userUpdateDTO.getPhone() != null) {
            user.setPhone(userUpdateDTO.getPhone());
        }
        if (userUpdateDTO.getLocation() != null) {
            user.setLocation(userUpdateDTO.getLocation());
        }
        if (userUpdateDTO.getDescription() != null) {
            user.setDescription(userUpdateDTO.getDescription());
        }
        if (userUpdateDTO.getAge() != null) {
            user.setAge(userUpdateDTO.getAge());
        }
        if (userUpdateDTO.getProfession() != null) {
            user.setProfession(userUpdateDTO.getProfession());
        }

        // Save the updated user
        return userRepository.save(user);
    }

    public CompteBancaire getCompteBancaireByEmail(String email) {
        Optional<User> userOpt = userRepository.findByMail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getCompteBancaire();  // Return the associated CompteBancaire
        }
        return null;  // or throw an exception if user is not found
    }







    // Fetch all users with their bank accounts and balances

}