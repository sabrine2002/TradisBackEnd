package tn.abt.tradis.models;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "reglements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reglement {

    @Id
    @Column(name = "id_reglement_iat", length = 50, nullable = false, unique = true)
    private String idReglementIat;  // Identifiant unique dans le système IAT

    @NotBlank
    @Column(name = "pays_reglement", length = 3, nullable = false)
    private String paysReglement;  // Code pays d'origine ou destination (ex: "TUN")

    @NotBlank
    @Column(name = "devise_reglement", length = 3, nullable = false)
    private String deviseReglement;  // Code devise (ex: "TND")

    @NotBlank
    @Column(name = "rib_compte", length = 50, nullable = false)
    private String ribCompte;  // RIB du compte bancaire

    @NotBlank
    @Column(name = "devise_compte", length = 3, nullable = false)
    private String deviseCompte;  // Devise du compte bancaire

    @NotNull
    @Column(name = "date_reglement", nullable = false)
    private LocalDate dateReglement;  // Date du règlement

    @NotNull
    @Column(name = "montant_reglement_cv", nullable = false)
    private Double montantReglementCv;  // Montant en monnaie convertible

    @NotBlank
    @Column(name = "Matricule fiscal ", length = 30, nullable = false)
    private String mf;  // Matricule fiscal du déclarant

    @Column(name = "lib_non_resident", length = 150)
    private String libNonResident;  // Nom du bénéficiaire non résident

    @NotNull
    @Column(name = "montant_reglement_dev", nullable = false)
    private Double montantReglementDev;  // Montant en devise locale

    @NotBlank
    @Column(name = "num_domiciliation", length = 20, nullable = false)
    private String numDomiciliation;  // Numéro de domiciliation lié au règlement

    @NotBlank
    @Column(name = "code_titre", length = 3, nullable = false)
    private String codeTitre;  // Code titre de commerce extérieur

    @NotNull
    @Column(name = "date_domiciliation", nullable = false)
    private LocalDate dateDomiciliation;  // Date domiciliation de l'opération

    @NotBlank
    @Column(name = "code_produit", length = 20, nullable = false)
    private String codeProduit;  // Code produit du règlement

    @Column(name = "libelle_produit", length = 150)
    private String libelleProduit;  // Libellé du produit du règlement

    @Column(name = "num_facture", length = 30)
    private String numFacture;  // Numéro facture du règlement

    @Column(name = "date_facture")
    private LocalDate dateFacture;  // Date facture

    @Column(name = "path_facture", length = 255)
    private String pathFacture;  // Chemin fichier facture si importée
}
