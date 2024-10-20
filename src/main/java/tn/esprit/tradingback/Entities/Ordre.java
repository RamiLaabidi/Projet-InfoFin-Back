package tn.esprit.tradingback.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tradingback.Entities.Enums.DEVISE;
import tn.esprit.tradingback.Entities.Enums.STATUT_ORDRE;
import tn.esprit.tradingback.Entities.Enums.TYPE_ORDRE;

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
public class Ordre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idO;
    String numO;
    @Enumerated(EnumType.STRING)
    TYPE_ORDRE typeOrdre;
    @Enumerated(EnumType.STRING)
    NATURE_ORDRE natureOrdre;
    @Enumerated(EnumType.STRING)
    DEVISE deviseOrdre;
    Date dateOrdre;
    Float quantite;
    Float prixExecution;
    @Enumerated(EnumType.STRING)
    STATUT_ORDRE statutOrdre;
    Float prixLimite;
    Float fraisTransaction;
    Date dateExpiration;

    @ManyToOne
    User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="ordre")
    private Set<Obligation> obligations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="ordre")
    private Set<Action> actions;

    @ManyToOne
    Marche marche;

}