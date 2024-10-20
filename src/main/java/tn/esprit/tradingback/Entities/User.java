package tn.esprit.tradingback.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tn.esprit.tradingback.Entities.Enums.ROLE;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usert")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idU;
    String nom;
    String prenom;
    Long numCin;
    Date dateDeNaissance;
    String paysDeNaissance;
    String nationalite;
    String adress;
    Float codePostal;
    Float contact;
    String photo;
    String mail;
    String motDePasse;
    @Enumerated(EnumType.STRING)
    ROLE role;

    @OneToOne
    Portfeuille portefeuille;

    @OneToOne
    Compte compteBancaire;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Ordre> ordres;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return mail;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    //   Compte + portfolio Abdou
    @OneToOne(mappedBy="utilisateur")
    private Portfeuille portfeuille;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="utilisateurC")
    private Set<Compte> comptes;


}
