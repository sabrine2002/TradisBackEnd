package tn.abt.tradis.Config;

import lombok.Getter;
import lombok.Setter;
import tn.abt.tradis.Entites.Settlement;
import tn.abt.tradis.Enum.SettlementStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
public class SettlementDTO {
    private Long idSettlement;
    private LocalDate settlementDate;
    private BigDecimal settlementAmountLC;
    private BigDecimal settlementAmountFC;
    private Integer settlementCountryIdParam;
    private Integer currencySettlementIdParam;
    private Integer settlementProductIdParam;
    private String titleNumDom;
    private String countryCode;
    private String currencyCode;
    private String productCode;
    private SettlementStatus status;
    // Constructeur depuis Settlement entity
    public SettlementDTO(Settlement settlement) {
        this.idSettlement = settlement.getIdSettlement();
        this.settlementAmountFC = settlement.getSettlementAmountFC();
        this.settlementAmountLC = settlement.getSettlementAmountLC();
        this.settlementDate = settlement.getSettlementDate();

        if (settlement.getSettlementCountry() != null) {

            this.countryCode = settlement.getSettlementCountry().getCnom();
        }

        if (settlement.getCurrencySettlement() != null) {
            this.currencyCode = settlement.getCurrencySettlement().getCnom();
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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
