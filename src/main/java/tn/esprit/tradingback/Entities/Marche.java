package tn.esprit.tradingback.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tradingback.Entities.Enums.DEVISE;
import tn.esprit.tradingback.Entities.Enums.STATUT_MARCHE;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Marche implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long idMarché;
    String nomMarché;
    String pays;
    @Enumerated(EnumType.STRING)
    DEVISE devisePrincipale;
    String heureOuverture;
    String heureCloture;
    @Enumerated(EnumType.STRING)
    STATUT_MARCHE statutMarche;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="marche")
    private Set<Ordre> ordres;
}
