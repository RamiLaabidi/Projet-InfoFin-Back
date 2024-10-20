package tn.esprit.tradingback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import tn.esprit.tradingback.Entities.*;
import tn.esprit.tradingback.Entities.Enums.STATUT_ORDRE;
import tn.esprit.tradingback.Repositories.ActionRepository;
import tn.esprit.tradingback.Repositories.OrdreRepository;
import tn.esprit.tradingback.Repositories.PortefeuilleRepository;
import tn.esprit.tradingback.Repositories.UserRepository;
import tn.esprit.tradingback.Services.Interfaces.IOrdreService;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequestMapping("/ordre")
@RequiredArgsConstructor
public class OrdreService implements IOrdreService {


    @Autowired
    private OrdreRepository ordreRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortefeuilleRepository portefeuilleRepository;

    @Transactional
    public Ordre passerOrdre(Long userId, Long actionId, Float quantite, NATURE_ORDRE natureOrdre, Float prixLimite) {
        // Fetch user and action, or throw if not found
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Action action = actionRepository.findById(actionId).orElseThrow(() -> new RuntimeException("Action not found"));

        // Get user's wallet and verify that it exists
        Portefeuille portefeuille = user.getPortefeuille();
        if (portefeuille == null) {
            throw new RuntimeException("User does not have a wallet.");
        }

        // Determine the execution price based on order type
        Float prixExecution;
        if (natureOrdre == NATURE_ORDRE.AU_MARCHE) {
            prixExecution = action.getPrixActuel(); // Market price
        } else if (natureOrdre == NATURE_ORDRE.LIMITE) {
            if (prixLimite == null) {
                throw new IllegalArgumentException("Limit price must be provided for a limit order.");
            }
            if (prixLimite >= action.getPrixActuel()) {
                prixExecution = prixLimite; // Use limit price if valid
            } else {
                throw new RuntimeException("Limit price cannot be lower than the current price.");
            }
        } else {
            throw new IllegalArgumentException("Invalid order type.");
        }

        // Calculate total order amount
        Float montantTotalOrdre = prixExecution * quantite;

        // Check if the user has enough funds in their wallet
        if (portefeuille.getCashDispo() < montantTotalOrdre) {
            throw new RuntimeException("Insufficient funds in wallet to place the order.");
        }

        // Create the order and set its properties
        Ordre ordre = new Ordre();
        ordre.setUser(user);
        ordre.setQuantite(quantite);
        ordre.setPrixExecution(prixExecution);
        ordre.setNatureOrdre(natureOrdre);
        ordre.setStatutOrdre(STATUT_ORDRE.EN_ATTENTE); // Order status set to 'pending'
        ordre.setDateOrdre(new Date());

        // Deduct funds from user's wallet
        portefeuille.setCashDispo(portefeuille.getCashDispo() - montantTotalOrdre);
        portefeuilleRepository.save(portefeuille); // Save the updated wallet

        // Match the order with the action
        ordre.setAction(action); // Set the action for the order

        // Save and return the order
        return ordreRepository.save(ordre);
    }



}
