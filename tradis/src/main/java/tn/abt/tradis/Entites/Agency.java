package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agences")
public class Agency implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id

    @Column(name = "code_agence", unique = true, nullable = false, length = 50)
    private String codeAgence;

    @Column(name = "nom", unique = true, nullable = false, length = 100)
    private String nom;

    @Column(name = "adresse", nullable = false, length = 150)
    private String adresse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_pays", nullable = false)
    private Pays pays;

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Client> clients;
}