package tn.abt.tradis.Entites;

import jakarta.persistence.*;
import lombok.*;
import tn.abt.tradis.Enum.RoleName;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName name; // ROLE_ADMIN, ROLE_AGENT

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
