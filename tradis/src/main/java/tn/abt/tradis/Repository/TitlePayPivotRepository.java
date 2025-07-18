package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.abt.tradis.Entites.TitlePayPivot;

import java.util.List;
import java.util.Optional;

public interface TitlePayPivotRepository extends JpaRepository<TitlePayPivot,Long> {
    @Query("SELECT p FROM TitlePayPivot p WHERE p.settlement.idSettlement = :idSettlement")
    Optional<TitlePayPivot> findBySettlementIdSettlement(@Param("idSettlement") Long idSettlement);

}
