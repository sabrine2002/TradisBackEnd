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

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "resident")
    private boolean resident;

    @Column(name = "accountNumber", nullable = false, length = 20) // numero de cpt
    private String accountNumber;

    private String agency;
    @Column(name = "accountCreationDate")
    private LocalDate accountCreationDate; //date creation compte

    @Column(name = "accountColsureDate")
    private LocalDate accountColsureDate; //date cloture compte

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountType", nullable = false, referencedColumnName = "idParam") // type de compte
    private Pnom accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DocumentType", nullable = false, referencedColumnName = "idParam") // type de doc
    private Pnom documentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountcurrency", nullable = false, referencedColumnName = "idParam") // devise de compte
    private Pnom accountcurrency;




    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TitlePayPivot> titlePayPivots;


}