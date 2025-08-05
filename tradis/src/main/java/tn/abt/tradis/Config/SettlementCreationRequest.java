package tn.abt.tradis.Config;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SettlementCreationRequest {

    private String titleId;
    private String settlementCountryCode;
    private String settlementCurrencyCode;
    private String productCode;
    private BigDecimal settlementAmountLocalCurrency;
    private BigDecimal settlementAmountForeignCurrency;
    private String accountRib;
    private String accountCurrency;
    private String taxIdentificationNumber;
    private String nonResidentLabel;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private String invoiceFilePath;

    // Getters
    public String getTitleId() {
        return titleId;
    }
    public String getSettlementCountryCode() {
        return settlementCountryCode;
    }
    public String getSettlementCurrencyCode() {
        return settlementCurrencyCode;
    }
    public String getProductCode() {
        return productCode;
    }
    public BigDecimal getSettlementAmountLocalCurrency() {
        return settlementAmountLocalCurrency;
    }
    public BigDecimal getSettlementAmountForeignCurrency() {
        return settlementAmountForeignCurrency;
    }
    public String getAccountRib() {
        return accountRib;
    }
    public String getAccountCurrency() {
        return accountCurrency;
    }
    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }
    public String getNonResidentLabel() {
        return nonResidentLabel;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }
    public String getInvoiceFilePath() {
        return invoiceFilePath;
    }

    // Setters
    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }
    public void setSettlementCountryCode(String settlementCountryCode) {
        this.settlementCountryCode = settlementCountryCode;
    }
    public void setSettlementCurrencyCode(String settlementCurrencyCode) {
        this.settlementCurrencyCode = settlementCurrencyCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public void setSettlementAmountLocalCurrency(BigDecimal settlementAmountLocalCurrency) {
        this.settlementAmountLocalCurrency = settlementAmountLocalCurrency;
    }
    public void setSettlementAmountForeignCurrency(BigDecimal settlementAmountForeignCurrency) {
        this.settlementAmountForeignCurrency = settlementAmountForeignCurrency;
    }
    public void setAccountRib(String accountRib) {
        this.accountRib = accountRib;
    }
    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }
    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }
    public void setNonResidentLabel(String nonResidentLabel) {
        this.nonResidentLabel = nonResidentLabel;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public void setInvoiceFilePath(String invoiceFilePath) {
        this.invoiceFilePath = invoiceFilePath;
    }

}