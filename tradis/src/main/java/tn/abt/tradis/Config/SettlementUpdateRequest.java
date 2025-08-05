package tn.abt.tradis.Config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.abt.tradis.Enum.SettlementStatus;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SettlementUpdateRequest {
    private String titleId;
    private BigDecimal settlementAmountLocalCurrency;
    private BigDecimal settlementAmountForeignCurrency;
    private LocalDate settlementDate;
    private String settlementCurrencyCode;
    private String settlementCountryCode;
    private String productCode;
    private SettlementStatus status;

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public BigDecimal getSettlementAmountLocalCurrency() {
        return settlementAmountLocalCurrency;
    }

    public void setSettlementAmountLocalCurrency(BigDecimal settlementAmountLocalCurrency) {
        this.settlementAmountLocalCurrency = settlementAmountLocalCurrency;
    }

    public BigDecimal getSettlementAmountForeignCurrency() {
        return settlementAmountForeignCurrency;
    }

    public void setSettlementAmountForeignCurrency(BigDecimal settlementAmountForeignCurrency) {
        this.settlementAmountForeignCurrency = settlementAmountForeignCurrency;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getSettlementCurrencyCode() {
        return settlementCurrencyCode;
    }

    public void setSettlementCurrencyCode(String settlementCurrencyCode) {
        this.settlementCurrencyCode = settlementCurrencyCode;
    }

    public String getSettlementCountryCode() {
        return settlementCountryCode;
    }

    public void setSettlementCountryCode(String settlementCountryCode) {
        this.settlementCountryCode = settlementCountryCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public SettlementStatus getStatus() {
        return status;
    }

    public void setStatus(SettlementStatus status) {
        this.status = status;
    }
}