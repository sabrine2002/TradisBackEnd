package tn.abt.tradis.Entites;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Settlement implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdSettlement;

    private LocalDate SettlementDate; // date de reglement

    @Column(precision = 19, scale = 4)
    private BigDecimal SettlementAmountLC; // montant de reglement devise

    @Column(precision = 19, scale = 4)
    private BigDecimal SettlementAmountFC; // montant de reglement convertible

    private String nonResidentName; //nom de non resident
    private String invoiceNumber; //numero facture
    private LocalDate invoiceDate; //date facture
    private String invoiceFilePath; // path facture

    @ManyToOne
    private Pnom SettlementCountry; // pays de reglement
    @ManyToOne
    @JoinColumn(name = "code_devise",referencedColumnName = "idParam")
    private Pnom CurrencySettlement;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Title title;



}