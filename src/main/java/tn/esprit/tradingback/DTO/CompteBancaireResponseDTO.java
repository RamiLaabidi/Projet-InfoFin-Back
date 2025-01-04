package tn.esprit.tradingback.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompteBancaireResponseDTO {
    private Long idCmpt;
    private Float numCompte;
    private String nomBanque;
    private Float soldeCompte;
    private String userName;  // The user's name that owns the bank account
}