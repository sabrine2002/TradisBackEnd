package tn.abt.tradis.Config;

import tn.abt.tradis.Entites.Pnom;

public class AgentDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String agencyCode;

    public AgentDTO() {}

    public AgentDTO(String firstname, String lastname, String email, Pnom agencyCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.agencyCode = String.valueOf(agencyCode);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }
}