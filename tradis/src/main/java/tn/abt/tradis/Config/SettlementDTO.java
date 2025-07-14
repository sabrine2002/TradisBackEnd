package tn.abt.tradis.Config;

import tn.abt.tradis.Entites.Settlement;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SettlementDTO {
    private Long idSettlement;
    private LocalDate settlementDate;
    private BigDecimal settlementAmountLC;
    private BigDecimal settlementAmountFC;
    private Integer settlementCountryIdParam;
    private Integer currencySettlementIdParam;
    private Integer settlementProductIdParam;
    private String titleNumDom;

    // Constructeur depuis Settlement entity
    public SettlementDTO(Settlement s) {
        this.idSettlement = s.getIdSettlement();
        this.settlementDate = s.getSettlementDate();
        this.settlementAmountLC = s.getSettlementAmountLC();
        this.settlementAmountFC = s.getSettlementAmountFC();
        this.settlementCountryIdParam = Math.toIntExact(s.getSettlementCountry() != null ? s.getSettlementCountry().getIdParam() : null);
        this.currencySettlementIdParam = Math.toIntExact(s.getCurrencySettlement() != null ? s.getCurrencySettlement().getIdParam() : null);
        this.settlementProductIdParam = Math.toIntExact(s.getSettlementProduct() != null ? s.getSettlementProduct().getIdParam() : null);
        this.titleNumDom = s.getTitle() != null ? s.getTitle().getNumDom() : null;
    }

    public Long getIdSettlement() {
        return idSettlement;
    }

    public void setIdSettlement(Long idSettlement) {
        this.idSettlement = idSettlement;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public BigDecimal getSettlementAmountLC() {
        return settlementAmountLC;
    }

    public void setSettlementAmountLC(BigDecimal settlementAmountLC) {
        this.settlementAmountLC = settlementAmountLC;
    }

    public BigDecimal getSettlementAmountFC() {
        return settlementAmountFC;
    }

    public void setSettlementAmountFC(BigDecimal settlementAmountFC) {
        this.settlementAmountFC = settlementAmountFC;
    }

    public Integer getSettlementCountryIdParam() {
        return settlementCountryIdParam;
    }

    public void setSettlementCountryIdParam(Integer settlementCountryIdParam) {
        this.settlementCountryIdParam = settlementCountryIdParam;
    }

    public Integer getCurrencySettlementIdParam() {
        return currencySettlementIdParam;
    }

    public void setCurrencySettlementIdParam(Integer currencySettlementIdParam) {
        this.currencySettlementIdParam = currencySettlementIdParam;
    }

    public Integer getSettlementProductIdParam() {
        return settlementProductIdParam;
    }

    public void setSettlementProductIdParam(Integer settlementProductIdParam) {
        this.settlementProductIdParam = settlementProductIdParam;
    }

    public String getTitleNumDom() {
        return titleNumDom;
    }

    public void setTitleNumDom(String titleNumDom) {
        this.titleNumDom = titleNumDom;
    }
}

