package tn.esprit.tradingback.Services.Interfaces;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import tn.esprit.tradingback.Entities.Enums.DEVISE;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortefeuilleDTO {

    @NotNull
    private Float cashDispo;

    @NotNull
    private Float valTotPortefeuille;

    @NotNull
    private Float rendementTotal;

    @NotNull
    private Float riskProfile;

    @NotNull
    private DEVISE devisesSupportees;

    @NotNull
    private Long compteBancaireId; // ID of the associated CompteBancaire
}
