package tn.esprit.tradingback.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tradingback.Entities.Amortissement;
@Repository

public interface AmortissementRepository extends JpaRepository<Amortissement, Long> {
}
