package tn.esprit.tradingback.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompteBancaireDTO {

    public Float getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(Float numCompte) {
        this.numCompte = numCompte;
    }

    public Float getSolde() {
        return solde;
    }

    public void setSolde(Float solde) {
        this.solde = solde;
    }

    private Float numCompte;
    private Float solde;

    public CompteBancaireDTO(Float numCompte, Float solde) {
        this.numCompte = numCompte;
        this.solde = solde;
    }

    // Getters and setters (or use Lombok's @Data for brevity)
}
