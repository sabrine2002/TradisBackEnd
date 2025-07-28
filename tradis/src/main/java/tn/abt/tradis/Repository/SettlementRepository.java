package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.abt.tradis.Entites.Settlement;

import java.util.List;

public interface SettlementRepository extends JpaRepository<Settlement, Long> {
   }
