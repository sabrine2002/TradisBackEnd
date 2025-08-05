package tn.abt.tradis.Config;

import lombok.Getter;
import lombok.Setter;
import tn.abt.tradis.Entites.Title;

@Getter
@Setter
public class TitleDTO {

    private String numDom;
    private String currencyTitle;

    public TitleDTO(Title title) {
        if (title != null) {
            this.numDom = title.getNumDom();
            if (title.getCurrencyTitle() != null) {
                this.currencyTitle = title.getCurrencyTitle().getLabel4();

            }

        }
    }

    public String getCurrencyTitle() {
        return currencyTitle;
    }

    public void setCurrencyTitle(String currencyTitle) {
        this.currencyTitle = currencyTitle;
    }

    public String getNumDom() {
        return numDom;
    }

    public void setNumDom(String numDom) {
        this.numDom = numDom;
    }
}