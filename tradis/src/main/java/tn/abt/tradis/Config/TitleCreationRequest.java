package tn.abt.tradis.Config;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TitleCreationRequest {
    @NotNull(message = "Client ID cannot be null")
    private Long clientId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    private String numDom;
    private String domYear;
    private LocalDate domDate;
    private LocalDate endTitleDate;
    private String contractNum;
    private LocalDate contractDate;
    private BigDecimal totalAmountCurr;
    private BigDecimal totalAmountTND;
    private Boolean isAdvancePayment;
    private BigDecimal advancePaymentAmount;


    private String titleStatusCode;
    private String titleCode;
    private String currencyCode;

    // Getters et Setters

    public String getNumDom() {
        return numDom;
    }

    public void setNumDom(String numDom) {
        this.numDom = numDom;
    }

    public String getDomYear() {
        return domYear;
    }

    public void setDomYear(String domYear) {
        this.domYear = domYear;
    }

    public LocalDate getDomDate() {
        return domDate;
    }

    public void setDomDate(LocalDate domDate) {
        this.domDate = domDate;
    }

    public LocalDate getEndTitleDate() {
        return endTitleDate;
    }

    public void setEndTitleDate(LocalDate endTitleDate) {
        this.endTitleDate = endTitleDate;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public BigDecimal getTotalAmountCurr() {
        return totalAmountCurr;
    }

    public void setTotalAmountCurr(BigDecimal totalAmountCurr) {
        this.totalAmountCurr = totalAmountCurr;
    }

    public BigDecimal getTotalAmountTND() {
        return totalAmountTND;
    }

    public void setTotalAmountTND(BigDecimal totalAmountTND) {
        this.totalAmountTND = totalAmountTND;
    }

    public Boolean getIsAdvancePayment() {
        return isAdvancePayment;
    }

    public void setIsAdvancePayment(Boolean isAdvancePayment) {
        this.isAdvancePayment = isAdvancePayment;
    }

    public BigDecimal getAdvancePaymentAmount() {
        return advancePaymentAmount;
    }

    public void setAdvancePaymentAmount(BigDecimal advancePaymentAmount) {
        this.advancePaymentAmount = advancePaymentAmount;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitleStatusCode() {
        return titleStatusCode;
    }

    public void setTitleStatusCode(String titleStatusCode) {
        this.titleStatusCode = titleStatusCode;
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
