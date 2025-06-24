package tn.abt.tradis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "produits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @Column(name = "code_produit", length = 10, nullable = false, unique = true)
    private String codeProduit;

    @NotBlank
    @Column(name = "libelle", length = 100, nullable = false)
    private String libelle;

    @Column(name = "type_produit", length = 50, nullable = false)
    private String typeProduit;  // Contiendra le code (ex : "LIBRE" ou "NON_LIBRE")


}
