package tn.abt.tradis.Entites;



import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Title implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "title_code", referencedColumnName = "idParam")
    private Pnom titleCode;
    @Id
    @Column(unique = true, length = 7)
    private String NumDom;
    private String DomYear;
    private LocalDate DomDate;
    private LocalDate EndTitleDate;


    private String ContractNum;
    private LocalDate ContractDate;


    @Column(precision = 19, scale = 4)
    private BigDecimal TotalAmountCurr; //montantTotalDevise
    @Column(precision = 19, scale = 4)
    private BigDecimal TotalAmountTND; // montantTotalTND
    @Column(precision = 19, scale = 4)
    private BigDecimal usedAmountCurr; // montant Utilise Devise
    @Column(precision = 19, scale = 4)
    private BigDecimal usedAmountTND ;  //montant Utilise TND
    @Column(precision = 19, scale = 4)
    private BigDecimal remainingAmountCurr; // montant Restant Devise
    @Column(precision = 19, scale = 4)
    private BigDecimal remainingAmountTND; //  montant Restant TND
    private Boolean isAdvancePayment = false; // Acompte oui/non
    @Column(precision = 19, scale = 4)
    private BigDecimal advancePaymentAmount; // montant Acompte
    private Boolean isCancelled  = false; // titre annule

    private LocalDate clearanceDate ; // date Apurement

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "title_status", referencedColumnName = "idParam")
    private Pnom titleStatus;



}


