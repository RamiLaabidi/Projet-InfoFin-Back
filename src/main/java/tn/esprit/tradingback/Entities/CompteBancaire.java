package tn.esprit.tradingback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompteBancaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCmpt;
    Float numCompte;
    String nomBanque;
    Date dateOuverture;
    Float soldeCompte;

    @OneToOne(mappedBy = "compteBancaire")
    @JsonManagedReference // This ensures serialization of 'compteBancaire' in 'User'
    private User user;

    @OneToMany(mappedBy = "compteBancaire")
    @JsonIgnoreProperties("compteBancaire")  // This ensures 'portefeuilles' list won't be serialized in CompteBancaire
    private List<Portefeuille> portefeuilles;  // One Comp

}
