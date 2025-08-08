package tn.abt.tradis.Config;
public class PnomDTO {
    private String code;
    private String label;

    public PnomDTO(String code, String label) {
        this.code = code;
        this.label = label;
    }

    // Getters et setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
}