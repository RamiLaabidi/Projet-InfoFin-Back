package tn.esprit.tradingback.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Action implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idA;
    String symBours;
    String nomEntreprise;
    String secteurAct;
    Float prixOuverture;
    Float prixClôture;
    Float prixActuel;
    Float variationBours;
    Float volumeEchanges;
    Date dateeDerniereTransaction;
    Float dividendeParAction;
    Float rendementDividende;
    Float actionsEnCircul;

    @ManyToOne
    Ordre ordre;
}
