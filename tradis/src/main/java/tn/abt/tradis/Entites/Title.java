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
    // Getters
    public String getNumDom() {
        return NumDom;
    }

    public String getDomYear() {
        return DomYear;
    }

    public LocalDate getDomDate() {
        return DomDate;
    }

    public LocalDate getEndTitleDate() {
        return EndTitleDate;
    }

    public String getContractNum() {
        return ContractNum;
    }

    public LocalDate getContractDate() {
        return ContractDate;
    }

    public BigDecimal getTotalAmountCurr() {
        return TotalAmountCurr;
    }

    public BigDecimal getTotalAmountTND() {
        return TotalAmountTND;
    }

    public BigDecimal getUsedAmountCurr() {
        return usedAmountCurr;
    }

    public BigDecimal getUsedAmountTND() {
        return usedAmountTND;
    }

    public BigDecimal getRemainingAmountCurr() {
        return remainingAmountCurr;
    }

    public BigDecimal getRemainingAmountTND() {
        return remainingAmountTND;
    }

    public Boolean getIsAdvancePayment() {
        return isAdvancePayment;
    }

    public BigDecimal getAdvancePaymentAmount() {
        return advancePaymentAmount;
    }

    public Boolean getIsCancelled() {
        return isCancelled;
    }

    public LocalDate getClearanceDate() {
        return clearanceDate;
    }

    public Client getClient() {
        return client;
    }

    public User getUser() {
        return user;
    }

    public Pnom getTitleStatus() {
        return titleStatus;
    }

    public Pnom getTitleCode() {
        return titleCode;
    }

    // Setters
    public void setNumDom(String numDom) {
        this.NumDom = numDom;
    }

    public void setDomYear(String domYear) {
        this.DomYear = domYear;
    }

    public void setDomDate(LocalDate domDate) {
        this.DomDate = domDate;
    }

    public void setEndTitleDate(LocalDate endTitleDate) {
        this.EndTitleDate = endTitleDate;
    }

    public void setContractNum(String contractNum) {
        this.ContractNum = contractNum;
    }

    public void setContractDate(LocalDate contractDate) {
        this.ContractDate = contractDate;
    }

    public void setTotalAmountCurr(BigDecimal totalAmountCurr) {
        this.TotalAmountCurr = totalAmountCurr;
    }

    public void setTotalAmountTND(BigDecimal totalAmountTND) {
        this.TotalAmountTND = totalAmountTND;
    }

    public void setUsedAmountCurr(BigDecimal usedAmountCurr) {
        this.usedAmountCurr = usedAmountCurr;
    }

    public void setUsedAmountTND(BigDecimal usedAmountTND) {
        this.usedAmountTND = usedAmountTND;
    }

    public void setRemainingAmountCurr(BigDecimal remainingAmountCurr) {
        this.remainingAmountCurr = remainingAmountCurr;
    }

    public void setRemainingAmountTND(BigDecimal remainingAmountTND) {
        this.remainingAmountTND = remainingAmountTND;
    }

    public void setIsAdvancePayment(Boolean isAdvancePayment) {
        this.isAdvancePayment = isAdvancePayment;
    }

    public void setAdvancePaymentAmount(BigDecimal advancePaymentAmount) {
        this.advancePaymentAmount = advancePaymentAmount;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public void setClearanceDate(LocalDate clearanceDate) {
        this.clearanceDate = clearanceDate;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitleStatus(Pnom titleStatus) {
        this.titleStatus = titleStatus;
    }

    public void setTitleCode(Pnom titleCode) {
        this.titleCode = titleCode;
    }
}

