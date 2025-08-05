package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client", unique = true, nullable = false, length = 7)
    private long idCli;


    @Column(name = "Firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name = "Lastname", length = 50)
    private String lastname;

    @Column(name = "nationality", length = 50)
    private String nationality; // Changed to String

    @Column(name = "resident")
    private boolean resident;

    @Column(name = "accountINT", nullable = false, length = 20)
    private String accountNumber; // Changed to match database column name

    @Column(name = "agency", length = 50)
    private String agency; // Changed to String

    @Column(name = "accountCreationDate")
    private LocalDate accountCreationDate;

    @Column(name = "accountColsureDate")
    private LocalDate accountColsureDate;

    @Column(name = "accountType", nullable = false, length = 50)
    private String accountType; // Changed to String

    @Column(name = "DocumentType", nullable = false, length = 50)
    private String documentType; // Changed to String

    @Column(name = "accountcurrency", nullable = false, length = 50)
    private String accountcurrency; // Changed to String

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TitlePayPivot> titlePayPivots;


    public long getIdCli() {
        return idCli;
    }

    public void setIdCli(long idCli) {
        this.idCli = idCli;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public LocalDate getAccountColsureDate() {
        return accountColsureDate;
    }

    public void setAccountColsureDate(LocalDate accountColsureDate) {
        this.accountColsureDate = accountColsureDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getAccountcurrency() {
        return accountcurrency;
    }

    public void setAccountcurrency(String accountcurrency) {
        this.accountcurrency = accountcurrency;
    }

    public List<TitlePayPivot> getTitlePayPivots() {
        return titlePayPivots;
    }

    public void setTitlePayPivots(List<TitlePayPivot> titlePayPivots) {
        this.titlePayPivots = titlePayPivots;
    }
}