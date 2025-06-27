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
    private Product product;

    @ManyToOne
    private Title title;



}