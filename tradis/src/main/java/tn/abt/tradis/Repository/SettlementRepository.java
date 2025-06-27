package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.abt.tradis.Entites.Settlement;

public interface SettlementRepository extends JpaRepository<Settlement, Long> {
}
