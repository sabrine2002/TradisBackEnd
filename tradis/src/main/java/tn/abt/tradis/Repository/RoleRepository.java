package tn.abt.tradis.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.abt.tradis.Entites.Role;
import tn.abt.tradis.Enum.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}