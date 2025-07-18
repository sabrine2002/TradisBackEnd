package tn.abt.tradis.Config;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SettlementUpdateRequest {
    private String titleId;
    private BigDecimal settlementAmountLocalCurrency;
    private BigDecimal settlementAmountForeignCurrency;
    private LocalDate settlementDate;
    private String settlementCurrencyCode;
    private String settlementCountryCode;
    private String productCode;

    // Getters
    public String getTitleId() {
        return titleId;
    }
    public BigDecimal getSettlementAmountLocalCurrency() {
        return settlementAmountLocalCurrency;
    }
    public BigDecimal getSettlementAmountForeignCurrency() {
        return settlementAmountForeignCurrency;
    }
    public LocalDate getSettlementDate() {
        return settlementDate;
    }
    public String getSettlementCurrencyCode() {
        return settlementCurrencyCode;
    }
    public String getSettlementCountryCode() {
        return settlementCountryCode;
    }
    public String getProductCode() {
        return productCode;
    }

    // Setters
    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }
    public void setSettlementAmountLocalCurrency(BigDecimal settlementAmountLocalCurrency) {
        this.settlementAmountLocalCurrency = settlementAmountLocalCurrency;
    }
    public void setSettlementAmountForeignCurrency(BigDecimal settlementAmountForeignCurrency) {
        this.settlementAmountForeignCurrency = settlementAmountForeignCurrency;
    }
    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }
    public void setSettlementCurrencyCode(String settlementCurrencyCode) {
        this.settlementCurrencyCode = settlementCurrencyCode;
    }
    public void setSettlementCountryCode(String settlementCountryCode) {
        this.settlementCountryCode = settlementCountryCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}