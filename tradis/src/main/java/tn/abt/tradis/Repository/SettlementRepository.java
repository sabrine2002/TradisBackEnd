package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.abt.tradis.Entites.Settlement;
import tn.abt.tradis.Enum.SettlementStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SettlementRepository extends JpaRepository<Settlement, Long> {
    @Query("SELECT COUNT(s) FROM Settlement s")
    long countAllSettlements();

    long countBySettlementStatus(SettlementStatus settlementStatus);

    @Query("SELECT s FROM Settlement s " +
            "WHERE (:startDate IS NULL OR s.SettlementDate >= :startDate) " +
            "AND (:endDate IS NULL OR s.SettlementDate <= :endDate) " +
            "AND (:minAmountLC IS NULL OR s.SettlementAmountLC >= :minAmountLC) " +
            "AND (:maxAmountLC IS NULL OR s.SettlementAmountLC <= :maxAmountLC) " +
            "AND (:minAmountFC IS NULL OR s.SettlementAmountFC >= :minAmountFC) " +
            "AND (:maxAmountFC IS NULL OR s.SettlementAmountFC <= :maxAmountFC) " +
            "AND (:countryId IS NULL OR s.SettlementCountry.idParam = :countryId) " +
            "AND (:currencyId IS NULL OR s.CurrencySettlement.idParam = :currencyId) " +
            "AND (:numDom IS NULL OR s.title.numDom = :numDom) " +
            "AND (:status IS NULL OR s.settlementStatus = :status)")
    List<Settlement> findByFilters(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("minAmountLC") BigDecimal minAmountLC,
            @Param("maxAmountLC") BigDecimal maxAmountLC,
            @Param("minAmountFC") BigDecimal minAmountFC,
            @Param("maxAmountFC") BigDecimal maxAmountFC,
            @Param("countryId") Long countryId,
            @Param("currencyId") Long currencyId,
            @Param("numDom") String numDom,
            @Param("status") SettlementStatus status
    );


}
