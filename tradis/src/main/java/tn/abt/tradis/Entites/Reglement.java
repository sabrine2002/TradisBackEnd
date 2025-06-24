package tn.abt.tradis.Entites;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Reglement implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pays_reglement_code")
    private Parametre paysReglement; // PAYS_REGLEMENT

    @ManyToOne
    @JoinColumn(name = "devise_compte_code")
    private Parametre deviseCompte; // DEVISE_COMPTE

    private LocalDate dateReglement; // DATE_REGLEMENT
    @Column(precision = 19, scale = 4)
    private BigDecimal montantReglementCV; // MONTANT_REGLEMENT_CV
    @Column(precision = 19, scale = 4)
    private BigDecimal montantReglementDev; // MONTANT_REGLEMENT_DEV

    private String libNonResident; // LIB_NON_RESIDENT
    private String numFacture; // NUM_FACTURE
    private LocalDate dateFacture; // DATE_FACTURE
    private String pathFacture; // PATH_FACTURE

    @ManyToOne
    @JoinColumn(name = "produit_code")
    private Produit produit; // CODE_PRODUIT, LIBELLE_PRODUIT

    @ManyToOne
    @JoinColumn(name = "titre_id")
    private Titre titre; // NUM_DOMICILIATION, CODE_TITRE, DATE_DOMICILIATION

    // Contrainte : deviseReglement doit correspondre à titre.devise
    // Contrainte : montantReglementDev <= titre.montantRestantDevise
}