package tn.abt.tradis.Config;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TitleCreationRequest {
    @NotNull(message = "Client ID cannot be null")
    private Long clientId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotEmpty(message = "NumDom cannot be empty")
    @Size(max = 7, message = "NumDom must be at most 7 characters")
    private String numDom;

    @NotEmpty(message = "DomYear cannot be empty")
    @Size(min = 4, max = 4, message = "DomYear must be 4 digits")
    private String domYear;

    @NotNull(message = "DomDate cannot be null")
    private LocalDate domDate;

    @NotNull(message = "EndTitleDate cannot be null")
    private LocalDate endTitleDate;

    @NotEmpty(message = "ContractNum cannot be empty")
    private String contractNum;

    @NotNull(message = "ContractDate cannot be null")
    private LocalDate contractDate;

    @NotNull(message = "TotalAmountCurr cannot be null")
    @DecimalMin(value = "0.01", message = "TotalAmountCurr must be at least 0.01")
    private BigDecimal totalAmountCurr;

    @NotNull(message = "TotalAmountTND cannot be null")
    @DecimalMin(value = "0.01", message = "TotalAmountTND must be at least 0.01")
    private BigDecimal totalAmountTND;

    private Boolean isAdvancePayment;

    @DecimalMin(value = "0.0", message = "AdvancePaymentAmount must be positive")
    private BigDecimal advancePaymentAmount;

    @NotEmpty(message = "TitleStatusCode cannot be empty")
    private String titleStatusCode;

    @NotEmpty(message = "TitleCode cannot be empty")
    private String titleCode;

    @NotEmpty(message = "CurrencyCode cannot be empty")
    private String currencyCode;

    // Getters et Setters
    public String getNumDom() { return numDom; }
    public void setNumDom(String numDom) { this.numDom = numDom; }
    public String getDomYear() { return domYear; }
    public void setDomYear(String domYear) { this.domYear = domYear; }
    public LocalDate getDomDate() { return domDate; }
    public void setDomDate(LocalDate domDate) { this.domDate = domDate; }
    public LocalDate getEndTitleDate() { return endTitleDate; }
    public void setEndTitleDate(LocalDate endTitleDate) { this.endTitleDate = endTitleDate; }
    public String getContractNum() { return contractNum; }
    public void setContractNum(String contractNum) { this.contractNum = contractNum; }
    public LocalDate getContractDate() { return contractDate; }
    public void setContractDate(LocalDate contractDate) { this.contractDate = contractDate; }
    public BigDecimal getTotalAmountCurr() { return totalAmountCurr; }
    public void setTotalAmountCurr(BigDecimal totalAmountCurr) { this.totalAmountCurr = totalAmountCurr; }
    public BigDecimal getTotalAmountTND() { return totalAmountTND; }
    public void setTotalAmountTND(BigDecimal totalAmountTND) { this.totalAmountTND = totalAmountTND; }
    public Boolean getIsAdvancePayment() { return isAdvancePayment; }
    public void setIsAdvancePayment(Boolean isAdvancePayment) { this.isAdvancePayment = isAdvancePayment; }
    public BigDecimal getAdvancePaymentAmount() { return advancePaymentAmount; }
    public void setAdvancePaymentAmount(BigDecimal advancePaymentAmount) { this.advancePaymentAmount = advancePaymentAmount; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getTitleStatusCode() { return titleStatusCode; }
    public void setTitleStatusCode(String titleStatusCode) { this.titleStatusCode = titleStatusCode; }
    public String getTitleCode() { return titleCode; }
    public void setTitleCode(String titleCode) { this.titleCode = titleCode; }
    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }
}