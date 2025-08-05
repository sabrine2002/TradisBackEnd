package tn.abt.tradis.Config;

public class DropdownOption {
    private Long id;
    private String label;
    private String code;

    // Constructeurs
    public DropdownOption() {}

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

    public DropdownOption(Long id, String label, String code) {
        this.id = id;
        this.label = label;
        this.code = code;
    }

}