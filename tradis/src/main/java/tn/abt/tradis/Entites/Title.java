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
public class Title implements Serializable {


    @Id
    @Column(unique = true, length = 7)
    private String NumDom; //numero de titre

    private String DomYear; // annee de creation du titre
    private LocalDate DomDate; //date de creation du titre
    private LocalDate EndTitleDate;// date fin du titre
    private String ContractNum;//numero de contrat.
    private LocalDate ContractDate;// Date d contrat
    private BigDecimal TotalAmountCurr; //montantTotalDevise
    private BigDecimal TotalAmountTND; // montantTotalTND
    private BigDecimal usedAmountCurr; // montant Utilise Devise
    private BigDecimal usedAmountTND;  //montant Utilise TND
    private BigDecimal remainingAmountCurr; // montant Restant Devise
    private BigDecimal remainingAmountTND; //  montant Restant TND
    private Boolean isAdvancePayment = false; // Acompte oui/non
    private BigDecimal advancePaymentAmount; // montant Acompte
    private Boolean isCancelled = false; // titre annule
    private LocalDate clearanceDate; // date Apurement

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "title_status", referencedColumnName = "idParam")
    private Pnom titleStatus; //etat du titre

    @ManyToOne
    @JoinColumn(name = "title_code", referencedColumnName = "idParam")
    private Pnom titleCode; //Le code titre

}


