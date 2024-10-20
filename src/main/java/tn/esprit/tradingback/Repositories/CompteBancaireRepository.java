package tn.esprit.tradingback.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tradingback.Entities.CompteBancaire;
@Repository

public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {
}
