package tn.esprit.tradingback.Controlleurs;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tradingback.DTO.UserUpdateDTO;
import tn.esprit.tradingback.Entities.Portefeuille;
import tn.esprit.tradingback.Entities.User;
import tn.esprit.tradingback.Services.UserService;

import java.util.Optional;

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
    @PutMapping("/{idU}/update")
    public ResponseEntity<User> updateUser(
            @PathVariable Long idU,
            @RequestBody UserUpdateDTO userUpdateDTO) {

        // Call the service to update the user's fields
        User updatedUser = userService.updateUser(idU, userUpdateDTO);

        // Return the updated user
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
