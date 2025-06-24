package tn.abt.tradis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "numero_compte", length = 30)
    private String numeroCompte;

    @Column(name = "devise_compte", length = 10)
    private String deviseCompte;

    // Suppression du champ agenceCompte qui est redondant avec la relation agence

    @NotBlank
    @Column(name = "nom", length = 100)
    private String nom;

    @NotBlank
    @Column(name = "prenom", length = 100)
    private String prenom;

    @Column(name = "date_creation_compte")
    private LocalDate dateCreationCompte;

    @Column(name = "date_cloture_compte")
    private LocalDate dateClotureCompte;

    @Column(name = "type_compte", length = 50)
    private String typeCompte;

    @Column(name = "type_document", length = 50)
    private String typeDocument;  // Ex : "CIN" ou "MATFISC"

    @Column(name = "numero_document", length = 50)
    private String numeroDocument;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agence_id")  // FK dans client vers agence
    private Agence agence;
}
