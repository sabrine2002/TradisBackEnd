package tn.abt.tradis.Config;

import lombok.Getter;
import lombok.Setter;
import tn.abt.tradis.Entites.Settlement;
import tn.abt.tradis.Enum.SettlementStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class SettlementWithLabelsDTO {

    private Long idSettlement;
    private BigDecimal settlementAmountForeignCurrency;
    private BigDecimal settlementAmountLocalCurrency;
    private LocalDate settlementDate;

    private String countryLabel;
    private String countryCode;
    private String currencyLabel;
    private String currencyCode;
    private String productCode;
    private String titleNumDom;

    private SettlementStatus status;
    private LocalDateTime lastUpdatedDate;

    public SettlementWithLabelsDTO() {}

    public SettlementWithLabelsDTO(Settlement settlement) {
        this.idSettlement = settlement.getIdSettlement();
        this.settlementAmountForeignCurrency = settlement.getSettlementAmountFC();
        this.settlementAmountLocalCurrency = settlement.getSettlementAmountLC();
        this.settlementDate = settlement.getSettlementDate();

        if (settlement.getSettlementCountry() != null) {
            this.countryLabel = settlement.getSettlementCountry().getLabel3();
            this.countryCode = settlement.getSettlementCountry().getCacc();
        }

        if (settlement.getCurrencySettlement() != null) {
            this.currencyLabel = settlement.getCurrencySettlement().getLabel4();
            this.currencyCode = settlement.getCurrencySettlement().getLabel4();
        }

        if (settlement.getSettlementProduct() != null) {
            this.productCode = settlement.getSettlementProduct().getCacc();
        }

        if (settlement.getTitle() != null) {
            this.titleNumDom = settlement.getTitle().getNumDom();
        }

        if (settlement.getSettlementStatus() != null) {
            this.status = settlement.getSettlementStatus();
        }

        this.lastUpdatedDate = settlement.getLastUpdatedDate();
    }
}