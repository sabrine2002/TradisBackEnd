package tn.abt.tradis.Config;

public class DropDownOptionT {

    private Long id;
    private Long code;
    private String label;


    // Constructeurs
    public DropDownOptionT() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public DropDownOptionT(Long id , Long code, String label) {
        this.id = id;
        this.code = code;
        this.label = label;

    }

}
