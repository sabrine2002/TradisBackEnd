package tn.abt.tradis.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Parametre implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String code; // Ex. "NON_APURE", "APURE", "021", "EUR"
    private String libelle; // Ex. "Non apuré", "Apuré", "Autorisation d’exportation", "Euro"
    private String type; // Ex. "ETAT_TITRE", "CODE_TITRE", "DEVISE", "TYPE_COMPTE"

    @OneToMany(mappedBy = "codeTitre")
    private List<Titre> titresCode;

    @OneToMany(mappedBy = "devise")
    private List<Titre> titresDevise;

    @OneToMany(mappedBy = "deviseContrat")
    private List<Titre> titresDeviseContrat;

    @OneToMany(mappedBy = "etatTitre")
    private List<Titre> titresEtat;

    @OneToMany(mappedBy = "typeDocument")
    private List<Client> clientsTypeDocument;

    @OneToMany(mappedBy = "typeCompte")
    private List<Client> clientsTypeCompte;

    @OneToMany(mappedBy = "deviseCompte")
    private List<Client> clientsDeviseCompte;

    @OneToMany(mappedBy = "typeProduit")
    private List<Produit> produits;

    @OneToMany(mappedBy = "paysReglement")
    private List<Reglement> reglementsPays;

    @OneToMany(mappedBy = "deviseReglement")
    private List<Reglement> reglementsDevise;

    @OneToMany(mappedBy = "deviseCompte")
    private List<Reglement> reglementsDeviseCompte;
}