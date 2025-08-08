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

    @ManyToOne
    @JoinColumn(name = "nationality",referencedColumnName = "id_param")
    private Pnom nationality; // Changed to String

    @Column(name = "resident")
    private boolean resident;

    @Column(name = "accountINT", nullable = false, length = 20)
    private String accountNumber; // Changed to match database column name

    @Column(name = "agency", length = 50)
    private String agency;

    @Column(name = "accountCreationDate")
    private LocalDate accountCreationDate;

    @Column(name = "accountColsureDate")
    private LocalDate accountColsureDate;

    @ManyToOne
    @JoinColumn(name = "accountType",referencedColumnName = "id_param")
    private Pnom accountType;

    @ManyToOne
    @JoinColumn(name = "documentType",referencedColumnName = "id_param")
    private Pnom DocumentType;

    @ManyToOne
    @JoinColumn(name = "accountcurrency",referencedColumnName = "id_param")
    private Pnom accountcurrency;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TitlePayPivot> titlePayPivots;

    public long getIdCli() {
        return idCli;
    }

    public void setIdCli(long idCli) {
        this.idCli = idCli;
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

    public Pnom getNationality() {
        return nationality;
    }

    public void setNationality(Pnom nationality) {
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

    public Pnom getAccountType() {
        return accountType;
    }

    public void setAccountType(Pnom accountType) {
        this.accountType = accountType;
    }

    public Pnom getAccountcurrency() {
        return accountcurrency;
    }

    public void setAccountcurrency(Pnom accountcurrency) {
        this.accountcurrency = accountcurrency;
    }

    public Pnom getDocumentType() {
        return DocumentType;
    }

    public void setDocumentType(Pnom documentType) {
        DocumentType = documentType;
    }

    public List<TitlePayPivot> getTitlePayPivots() {
        return titlePayPivots;
    }

    public void setTitlePayPivots(List<TitlePayPivot> titlePayPivots) {
        this.titlePayPivots = titlePayPivots;
    }
}