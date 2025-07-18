package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "settlement")
public class Settlement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSettlement;

    private LocalDate SettlementDate;

    @Column(precision = 19, scale = 4)
    private BigDecimal SettlementAmountLC;

    @Column(precision = 19, scale = 4)
    private BigDecimal SettlementAmountFC;

    @ManyToOne
    @JoinColumn(name = "settlement_country_id_param", referencedColumnName = "id_param")
    private Pnom SettlementCountry;

    @ManyToOne
    @JoinColumn(name = "code_devise", referencedColumnName = "id_param")
    private Pnom CurrencySettlement;

    @ManyToOne
    @JoinColumn(name = "settlement_product_id_param", referencedColumnName = "id_param")
    private Pnom SettlementProduct;

    @ManyToOne
    @JoinColumn(name = "title_num_dom", referencedColumnName = "numDom")
    private Title title;

    // Getters
    public Long getIdSettlement() {
        return idSettlement;
    }

    public LocalDate getSettlementDate() {
        return SettlementDate;
    }

    public BigDecimal getSettlementAmountLC() {
        return SettlementAmountLC;
    }

    public BigDecimal getSettlementAmountFC() {
        return SettlementAmountFC;
    }

    public Pnom getSettlementCountry() {
        return SettlementCountry;
    }

    public Pnom getCurrencySettlement() {
        return CurrencySettlement;
    }

    public Pnom getSettlementProduct() {
        return SettlementProduct;
    }

    public Title getTitle() {
        return title;
    }

    // Setters
    public void setIdSettlement(Long idSettlement) {
        this.idSettlement = idSettlement;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.SettlementDate = settlementDate;
    }

    public void setSettlementAmountLC(BigDecimal settlementAmountLC) {
        this.SettlementAmountLC = settlementAmountLC;
    }

    public void setSettlementAmountFC(BigDecimal settlementAmountFC) {
        this.SettlementAmountFC = settlementAmountFC;
    }

    public void setSettlementCountry(Pnom settlementCountry) {
        this.SettlementCountry = settlementCountry;
    }

    public void setCurrencySettlement(Pnom currencySettlement) {
        this.CurrencySettlement = currencySettlement;
    }

    public void setSettlementProduct(Pnom settlementProduct) {
        this.SettlementProduct = settlementProduct;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}