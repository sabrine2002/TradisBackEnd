package tn.abt.tradis.Config;

public class DropdownOptionTitle {
    private Long id;
    private String code;
    private String label;

    // Constructeurs
    public DropdownOptionTitle() {}

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DropdownOptionTitle(Long id, String code, String label) {
        this.id = id;
        this.code = code;
        this.label = label;

    }

}
