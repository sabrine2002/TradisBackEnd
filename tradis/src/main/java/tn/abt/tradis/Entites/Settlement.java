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
@Table(name = "")
public class Settlement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdSettlement;

    private LocalDate SettlementDate; // date de reglement

    @Column(precision = 19, scale = 4)
    private BigDecimal SettlementAmountLC; // montant de reglement devise

    @Column(precision = 19, scale = 4)
    private BigDecimal SettlementAmountFC; // montant de reglement convertible

//    private String invoiceNumber; //numero facture
//    private LocalDate invoiceDate; //date facture
//    private String invoiceFilePath; // path facture

    @ManyToOne
    @JoinColumn(referencedColumnName = "idParam")
    private Pnom SettlementCountry; // pays de reglement
    @ManyToOne
    @JoinColumn(name = "code_devise",referencedColumnName = "idParam")
    private Pnom CurrencySettlement;

    @ManyToOne
    @JoinColumn(referencedColumnName = "idParam")
    private Pnom SettlementProduct;

    @ManyToOne
    private Title title;


    // Getters
    public Long getIdSettlement() {
        return IdSettlement;
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
        this.IdSettlement = idSettlement;
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