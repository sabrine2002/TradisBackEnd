package tn.abt.tradis.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TITRE")
public class Title {

    @Id
    @Column(name = "NUMDOM", length = 7, nullable = false, unique = true)
    private String numDom;

    @NotNull
    @Column(name = "ANNEEDOM", nullable = false, unique = true)
    private Integer anneeDom;

    @NotNull
    @Column(name = "DATEDOM", nullable = false)
    private LocalDate dateDom;

    @Column(name = "DATFINTIT")
    private LocalDate dateFinTit;

    @NotNull
    @Column(name = "NATTIT", length = 3, nullable = false, unique = true)
    private String natTit;

    @NotNull
    @Column(name = "NUMCPT", nullable = false)
    private String numCpt;

    @Column(name = "MATFISC")
    private String matFisc;

    @Column(name = "CODDEV_REGL")
    private String codDevRegl;

    @Column(name = "NUMCNT")
    private String numCnt;

    @Column(name = "DATCNT")
    private LocalDate datCnt;

    @Column(name = "CODDEV_CNT")
    private String codDevCnt;

    @Column(name = "MNTPTFNDEV")
    private Double mntPtfnDev;

    @Column(name = "MNTPTFNTND")
    private Double mntPtfnTnd;

    @Column(name = "MNTUTIL")
    private Double mntUtil;

    @Column(name = "MNTUTILTND")
    private Double mntUtilTnd;

    @Column(name = "MNTRES")
    private Double mntRes;

    @Column(name = "MNTRESTND")
    private Double mntResTnd;

    @Column(name = "ETATTITRE")
    private String etatTitre;

    @Column(name = "DATAPU")
    private LocalDate dateApu;

    @Column(name = "UTILISATEUR")
    private String utilisateur;

    @Column(name = "TITRE_ANNULE")
    private Boolean titreAnnule;

    @Column(name = "ACOMPTE")
    private Boolean acompte;

    @Column(name = "MONTANT_ACOMPTE")
    private Double montantAcompte;
}

