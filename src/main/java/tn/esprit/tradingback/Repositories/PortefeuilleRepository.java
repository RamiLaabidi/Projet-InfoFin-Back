package tn.esprit.tradingback.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tradingback.Entities.Marche;
import tn.esprit.tradingback.Entities.Portefeuille;
@Repository

public interface PortefeuilleRepository extends JpaRepository<Portefeuille, Long> {
}
