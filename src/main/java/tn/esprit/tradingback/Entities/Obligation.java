package tn.esprit.tradingback.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tradingback.Entities.Enums.MODALITE;
import tn.esprit.tradingback.Entities.Enums.STATUT_OBLIGATION;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Obligation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idOblig;
    Float nomEmetteur;
    Float tauxInteret;
    @Enumerated(EnumType.STRING)
    MODALITE modaliteRemboursement;
    Date dateEmission;
    Float valeurNominale;
    @Enumerated(EnumType.STRING)
    STATUT_OBLIGATION statutOblig;

    @ManyToOne
    Ordre ordre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="obligation")
    private Set<Amortissement> amortissements;


}
