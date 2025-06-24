package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.Data;
import tn.abt.tradis.Enum.RoleName;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class Role implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Enumerated(EnumType.STRING)
        private RoleName nom; // ROLE_ADMIN, ROLE_AGENT

        @ManyToMany(mappedBy = "roles")
        private Set<Utilisateur> utilisateurs;
}
