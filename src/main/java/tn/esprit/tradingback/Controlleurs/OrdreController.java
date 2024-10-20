package tn.esprit.tradingback.Controlleurs;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tradingback.Entities.NATURE_ORDRE;
import tn.esprit.tradingback.Entities.Ordre;
import tn.esprit.tradingback.Services.OrdreService;
@RestController
@RequestMapping("/ordre")
@RequiredArgsConstructor
public class OrdreController {
    @Autowired
    private OrdreService ordreService;

    // Endpoint pour passer un ordre
    @PostMapping("/passer/{userId}/{actionId}")
    public ResponseEntity<Ordre> passerOrdre(
            @PathVariable Long userId,
            @PathVariable Long actionId,
            @RequestParam Float quantite,
            @RequestParam NATURE_ORDRE natureOrdre,
            @RequestParam(required = false) Float prixLimite) {

        // Ensure quantity is positive
        if (quantite <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Ordre ordre = ordreService.passerOrdre(userId, actionId, quantite, natureOrdre, prixLimite);
        return ResponseEntity.ok(ordre);
    }

}
