package tn.abt.tradis.Config;

import lombok.Getter;
import lombok.Setter;
import tn.abt.tradis.Entites.Title;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TitleWithLabelsDTO {

    private String numDom;
    private String clientName;
    private LocalDate domDate;

    private String codeLabel;
    private String statusLabel;
    private String currencyLabel;
    private String currencyCode;

    private BigDecimal totalAmountTND;

    public TitleWithLabelsDTO() {}

    public TitleWithLabelsDTO(Title title) {
        this.numDom = title.getNumDom();
        this.domDate = title.getDomDate();
        this.totalAmountTND = title.getTotalAmountTND();

        if (title.getClient() != null) {
            this.clientName = title.getClient().getFirstname(); // ou getRaisonSociale() selon ton entité
        }

        if (title.getTitleCode() != null) {
            this.codeLabel = title.getTitleCode().getLabel1();
        }

        if (title.getTitleStatus() != null) {
            this.statusLabel = title.getTitleStatus().getLabel1();
        }

        if (title.getCurrencyTitle() != null) {
            this.currencyLabel = title.getCurrencyTitle().getLabel4();
            this.currencyCode = title.getCurrencyTitle().getLabel4(); // ou .getCacc() si tu veux le code réel
        }
    }

    // Getters et setters explicites (si nécessaire)
    public String getNumDom() {
        return numDom;
    }

    public void setNumDom(String numDom) {
        this.numDom = numDom;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDate getDomDate() {
        return domDate;
    }

    public void setDomDate(LocalDate domDate) {
        this.domDate = domDate;
    }

    public String getCodeLabel() {
        return codeLabel;
    }

    public void setCodeLabel(String codeLabel) {
        this.codeLabel = codeLabel;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    public String getCurrencyLabel() {
        return currencyLabel;
    }

    public void setCurrencyLabel(String currencyLabel) {
        this.currencyLabel = currencyLabel;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getTotalAmountTND() {
        return totalAmountTND;
    }

    public void setTotalAmountTND(BigDecimal totalAmountTND) {
        this.totalAmountTND = totalAmountTND;
    }
}
