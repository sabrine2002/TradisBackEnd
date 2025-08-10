package tn.abt.tradis.Auth.Reponse;

import tn.abt.tradis.Config.AgencyDto;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String lastname;
    private String email;
    private AgencyDto agency;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, String lastname, AgencyDto agency, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.lastname = lastname;
        this.agency = agency;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public AgencyDto getAgency() { return agency; }
    public void setAgency(AgencyDto agency) { this.agency = agency; }

    public List<String> getRoles() {
        return roles;
    }
}