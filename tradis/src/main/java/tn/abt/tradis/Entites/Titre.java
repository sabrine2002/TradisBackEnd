package tn.abt.tradis.Entites;



import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Titre implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true, length = 7)
    private String numDom; // NUMDOM, généré  sur 7 chiffres
    private String anneeDom; // ANNEEDOM
    private LocalDate dateDom; // DATEDOM
    private LocalDate datFinTit; // DATFINTIT

    @ManyToOne
    @JoinColumn(name = "code_titre")
    private Parametre codeTitre; // NATTIT (021, 022, 031, 033)



    private String numContrat; // NUMCNT, facultatif
    private LocalDate dateContrat; // DATCNT, facultatif

    @ManyToOne
    @JoinColumn(name = "devise_contrat_code")
    private Parametre deviseContrat; // CODDEV_CNT

    @Column(precision = 19, scale = 4)
    private BigDecimal montantTotalDevise; // MNTPTFNDEV
    @Column(precision = 19, scale = 4)
    private BigDecimal montantTotalTND; // MNTPTFNTND
    @Column(precision = 19, scale = 4)
    private BigDecimal montantUtiliseDevise = BigDecimal.ZERO; // MNTUTIL
    @Column(precision = 19, scale = 4)
    private BigDecimal montantUtiliseTND = BigDecimal.ZERO; // MNTUTILTND
    @Column(precision = 19, scale = 4)
    private BigDecimal montantRestantDevise; // MNTRES
    @Column(precision = 19, scale = 4)
    private BigDecimal montantRestantTND; // MNTRESTND

    private Boolean acompte = false; // Acompte OUI/NON
    @Column(precision = 19, scale = 4)
    private BigDecimal montantAcompte; // MONTANT_ACOMPTE
    private Boolean titreAnnule = false; // TITRE_ANNULE

    @ManyToOne
    @JoinColumn(name = "etat_titre_code")
    private Parametre etatTitre; // ETATTITRE : Référence à Parametre (NON_APURE, APURE)

    private LocalDate dateApurement; // DATAPU

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client; // NUMCPT, MATFISC

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User user; // UTILISATEUR (créateur du titre)

    @OneToMany(mappedBy = "titre", cascade = CascadeType.ALL)
    private List<Reglement> reglements;
}


