package tn.abt.tradis.Config;

import tn.abt.tradis.Entites.Title;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TitleUpdateDTO {
    private String numDom;
    private String domYear;
    private LocalDate domDate;
    private LocalDate endTitleDate;
    private String contractNum;
    private LocalDate contractDate;
    private BigDecimal totalAmountCurr;
    private BigDecimal totalAmountTND;
    private BigDecimal advancePaymentAmount;
    private String titleCode;
    private String titleStatusCode;
    private String currencyCode;
    private Long clientId;
    private String clientName;
    private Long userId;

    public TitleUpdateDTO(Title title) {
        this.numDom = title.getNumDom();
        this.domYear = title.getDomYear();
        this.domDate = title.getDomDate();
        this.endTitleDate = title.getEndTitleDate();
        this.contractNum = title.getContractNum();
        this.contractDate = title.getContractDate();
        this.totalAmountCurr = title.getTotalAmountCurr();
        this.totalAmountTND = title.getTotalAmountTND();
        this.advancePaymentAmount = title.getAdvancePaymentAmount();
        this.titleCode = title.getTitleCode() != null ? title.getTitleCode().getCacc() : null;
        this.titleStatusCode = title.getTitleStatus() != null ? title.getTitleStatus().getCacc() : null;
        this.currencyCode = title.getCurrencyTitle() != null ? title.getCurrencyTitle().getLabel4() : null;
        this.clientId = title.getClient() != null ? title.getClient().getIdCli() : null;
        this.clientName = title.getClient() != null ? title.getClient().getFirstname() : null;
    }

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

    public BigDecimal getAdvancePaymentAmount() {
        return advancePaymentAmount;
    }

    public void setAdvancePaymentAmount(BigDecimal advancePaymentAmount) {
        this.advancePaymentAmount = advancePaymentAmount;
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public String getTitleStatusCode() {
        return titleStatusCode;
    }

    public void setTitleStatusCode(String titleStatusCode) {
        this.titleStatusCode = titleStatusCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}