package tn.abt.tradis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agences")
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nom", nullable = false, unique = true, length = 100)
    private String nom;

    @NotBlank
    @Column(name = "adresse", nullable = false, length = 150)
    private String adresse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_pays", nullable = false)
    private Pays pays;

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Agent> agents;

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Client> clients;
}
