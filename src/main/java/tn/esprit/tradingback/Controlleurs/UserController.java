package tn.esprit.tradingback.Controlleurs;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tradingback.Entities.Portefeuille;
import tn.esprit.tradingback.Entities.User;
import tn.esprit.tradingback.Services.UserService;

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
}
