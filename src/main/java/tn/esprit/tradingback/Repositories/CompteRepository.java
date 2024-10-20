package tn.esprit.tradingback.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tradingback.Entities.Compte;
@Repository

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
