package tn.esprit.tradingback.Controlleurs;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tradingback.DTO.UserUpdateDTO;
import tn.esprit.tradingback.Entities.CompteBancaire;
import tn.esprit.tradingback.Entities.Portefeuille;
import tn.esprit.tradingback.Entities.User;
import tn.esprit.tradingback.Services.UserService;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200") // Le port Angular par défaut
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{idU}/portefeuille")
    public ResponseEntity<User> assignPortefeuilleToUser(
            @PathVariable Long idU,
            @RequestBody Portefeuille portefeuille) {

        // Call the service to assign the portefeuille to the user
        User updatedUser = userService.assignPortefeuilleToUser(idU, portefeuille);

        // Return the updated user as the response
        return ResponseEntity.ok(updatedUser);
    }

    // New API to update user profile fields
    @PutMapping("/update-by-email")
    public ResponseEntity<User> updateUserByEmail(
            @RequestParam String email,
            @RequestBody UserUpdateDTO userUpdateDTO) {

        User updatedUser = userService.updateUserByEmail(email, userUpdateDTO);

        return ResponseEntity.ok(updatedUser);
    }


    @GetMapping("/by-email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {

        // Call the service to fetch the user by email
        Optional<User> userOptional = userService.getUserByEmail(email);

        // If user is found, return the user; otherwise return 404 Not Found
        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
