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
    private String idCli;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name="nationalité")
    private String nationalit;

    @Column(name="resident")
    private boolean resident;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_compte_code", nullable = false)
    private Parametre typeCompte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_document_code", nullable = false)
    private Parametre typeDocument;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "devise_code", nullable = false)
    private Parametre deviseCompte;



    @Column(name = "numero_compte", nullable = false, length = 50)
    private String numeroCompte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_agence", nullable = false)
    private Agency agence;

    @Column(name = "date_creation_compte")
    private LocalDate dateCreationCompte;

    @Column(name = "date_cloture_compte")
    private LocalDate dateClotureCompte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_pays", nullable = false)
    private Pays pays;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Titre> titres;
}