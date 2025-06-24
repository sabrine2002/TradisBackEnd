package tn.abt.tradis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "pays")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pays {

    @Id
    @Column(name = "code_pays", length = 3, nullable = false, unique = true)
    @Pattern(regexp = "^[A-Z]{3}$", message = "Le code pays doit contenir 3 lettres majuscules")
    private String codePays;

    @NotNull
    @Column(name = "code_devise", length = 3, nullable = false)
    private String codeDevise;

    @NotBlank
    @Column(name = "libelle", length = 100, nullable = false)
    private String libelle;


}