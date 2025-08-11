package tn.abt.tradis.Entites;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import tn.abt.tradis.Enum.TitileStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Title implements Serializable {


    @Id
    @Column(name = "numDom", unique = true, length = 7)
    private String numDom; //numero de titre
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
    @Column(name = "last_updated_date", nullable = true)
    private LocalDateTime lastUpdatedDate ;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "title_status", referencedColumnName = "id_param")
    private Pnom titleStatus; //etat du titre

    @ManyToOne
    @JoinColumn(name = "title_code", referencedColumnName = "id_param")
    private Pnom titleCode; //Le code titre

    @ManyToOne
    @JoinColumn(name = "code_devise", referencedColumnName = "id_param")
    private Pnom CurrencyTitle;
    @ManyToOne
    @JoinColumn(name = "titile_Status", referencedColumnName = "id_param")
    private Pnom titileStatus;

    @JsonProperty("titleStatus")
    public Long getTitleStatusId() {
        return titleStatus != null ? titleStatus.getIdParam() : null;
    }

    public String getNumDom() {
        return numDom;
    }

    public void setNumDom(String numDom) {
        this.numDom = numDom;
    }

    public String getDomYear() {
        return DomYear;
    }

    public void setDomYear(String domYear) {
        DomYear = domYear;
    }

    public LocalDate getDomDate() {
        return DomDate;
    }

    public void setDomDate(LocalDate domDate) {
        DomDate = domDate;
    }

    public LocalDate getEndTitleDate() {
        return EndTitleDate;
    }

    public void setEndTitleDate(LocalDate endTitleDate) {
        EndTitleDate = endTitleDate;
    }

    public LocalDate getContractDate() {
        return ContractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        ContractDate = contractDate;
    }

    public String getContractNum() {
        return ContractNum;
    }

    public void setContractNum(String contractNum) {
        ContractNum = contractNum;
    }

    public BigDecimal getTotalAmountCurr() {
        return TotalAmountCurr;
    }

    public void setTotalAmountCurr(BigDecimal totalAmountCurr) {
        TotalAmountCurr = totalAmountCurr;
    }

    public BigDecimal getTotalAmountTND() {
        return TotalAmountTND;
    }

    public void setTotalAmountTND(BigDecimal totalAmountTND) {
        TotalAmountTND = totalAmountTND;
    }

    public BigDecimal getUsedAmountCurr() {
        return usedAmountCurr;
    }

    public void setUsedAmountCurr(BigDecimal usedAmountCurr) {
        this.usedAmountCurr = usedAmountCurr;
    }

    public BigDecimal getUsedAmountTND() {
        return usedAmountTND;
    }

    public void setUsedAmountTND(BigDecimal usedAmountTND) {
        this.usedAmountTND = usedAmountTND;
    }

    public BigDecimal getRemainingAmountCurr() {
        return remainingAmountCurr;
    }

    public void setRemainingAmountCurr(BigDecimal remainingAmountCurr) {
        this.remainingAmountCurr = remainingAmountCurr;
    }

    public BigDecimal getRemainingAmountTND() {
        return remainingAmountTND;
    }

    public void setRemainingAmountTND(BigDecimal remainingAmountTND) {
        this.remainingAmountTND = remainingAmountTND;
    }

    public Boolean getAdvancePayment() {
        return isAdvancePayment;
    }

    public void setAdvancePayment(Boolean advancePayment) {
        isAdvancePayment = advancePayment;
    }

    public BigDecimal getAdvancePaymentAmount(BigDecimal advancePaymentAmount) {
        return this.advancePaymentAmount;
    }

    public void setAdvancePaymentAmount(BigDecimal advancePaymentAmount) {
        this.advancePaymentAmount = advancePaymentAmount;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
    }

    public LocalDate getClearanceDate() {
        return clearanceDate;
    }

    public void setClearanceDate(LocalDate clearanceDate) {
        this.clearanceDate = clearanceDate;
    }

    public Pnom getCurrencyTitle() {
        return CurrencyTitle;
    }

    public void setCurrencyTitle(Pnom currencyTitle) {
        CurrencyTitle = currencyTitle;
    }

    public Pnom getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(Pnom titleCode) {
        this.titleCode = titleCode;
    }

    public Pnom getTitleStatus() {
        return titleStatus;
    }

    public void setTitleStatus(Pnom titleStatus) {
        this.titleStatus = titleStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public BigDecimal getAdvancePaymentAmount() {
        return advancePaymentAmount;
    }


}
