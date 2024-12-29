package tn.esprit.tradingback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import tn.esprit.tradingback.Entities.CompteBancaire;
import tn.esprit.tradingback.Entities.Portefeuille;
import tn.esprit.tradingback.Repositories.CompteBancaireRepository;
import tn.esprit.tradingback.Repositories.PortefeuilleRepository;
import tn.esprit.tradingback.Services.Interfaces.PortefeuilleDTO;

@Service
@RequiredArgsConstructor
public class PortefeuilleService {

    private final PortefeuilleRepository portefeuilleRepository;
    private final CompteBancaireRepository compteBancaireRepository;

    public Portefeuille createPortefeuille(PortefeuilleDTO portefeuilleDTO) {
        // Fetch the CompteBancaire by ID
        CompteBancaire compteBancaire = compteBancaireRepository.findById(portefeuilleDTO.getCompteBancaireId())
                .orElseThrow(() -> new RuntimeException("CompteBancaire not found with id " + portefeuilleDTO.getCompteBancaireId()));

        // Map DTO to Portefeuille entity
        Portefeuille portefeuille = Portefeuille.builder()
                .cashDispo(portefeuilleDTO.getCashDispo())
                .valTotPortefeuille(portefeuilleDTO.getValTotPortefeuille())
                .rendementTotal(portefeuilleDTO.getRendementTotal())
                .riskProfile(portefeuilleDTO.getRiskProfile())
                .devisesSupportees(portefeuilleDTO.getDevisesSupportees())
                .compteBancaire(compteBancaire)
                .build();

        // Save the Portefeuille entity
        return portefeuilleRepository.save(portefeuille);
    }
}