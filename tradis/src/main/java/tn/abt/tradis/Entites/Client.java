package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client", unique = true, nullable = false, length = 7)
    private long idCli;

    @Column(name = "Firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name = "Lastname", length = 50)
    private String lastname;

    @Column(name="nationality")
    private String nationality;

    @Column(name="resident")
    private boolean resident;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountType", nullable = false,referencedColumnName = "code_param") // type de compte
    private Pnom accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DocumentType", nullable = false,referencedColumnName = "code_param") // type de doc
    private Pnom documentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountcurrency", nullable = false,referencedColumnName = "code_param") // devise de compte
    private Pnom accountcurrency;

    @Column(name = "accountNumber", nullable = false, length =20) // numero de cpt
    private String accountNumber;

    private String agency;
    @Column(name = "accountCreationDate")
    private LocalDate accountCreationDate; //date creation compte

    @Column(name = "accountColsureDate")
    private LocalDate accountColsureDate; //date cloture compte


    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Title> titles;


}