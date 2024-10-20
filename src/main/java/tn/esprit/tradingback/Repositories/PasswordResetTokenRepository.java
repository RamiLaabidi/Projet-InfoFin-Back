package tn.esprit.tradingback.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tradingback.Entities.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
