package tn.esprit.tradingback.Controlleurs;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tradingback.Entities.Portefeuille;
import tn.esprit.tradingback.Services.Interfaces.PortefeuilleDTO;
import tn.esprit.tradingback.Services.PortefeuilleService;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200") // Le port Angular par défaut
@RestController
@RequestMapping("/user/portefeuille")
@RequiredArgsConstructor
public class PortefeuilleController {

    private final PortefeuilleService portefeuilleService;

    @PostMapping
    public ResponseEntity<?> createPortefeuille(@RequestBody PortefeuilleDTO portefeuilleDTO) {
        try {
            Portefeuille savedPortefeuille = portefeuilleService.createPortefeuille(portefeuilleDTO);
            return new ResponseEntity<>(savedPortefeuille, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}