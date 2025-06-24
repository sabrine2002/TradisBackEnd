package tn.abt.tradis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "parametres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Param {

    @Id
    @Column(name = "code", length = 50, nullable = false, unique = true)
    private String code;

    @NotBlank
    @Column(name = "libelle", length = 150, nullable = false)
    private String libelle;

    @NotBlank
    @Column(name = "groupe", length = 50, nullable = false)
    private String groupe;
}
