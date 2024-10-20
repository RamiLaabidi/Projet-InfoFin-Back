package tn.esprit.tradingback.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tradingback.Entities.Enums.DEVISE;

import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Portefeuille implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idP;
    Float cashDispo;
    Float valTotPortefeuille;
    Float rendementTotal;
    Float riskProfile;
    @Enumerated(EnumType.STRING)
    DEVISE devisesSupportees;
   // List<Action> listeActions;
    //List<Ordre>  ListOrdres;
}
