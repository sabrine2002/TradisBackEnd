package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.abt.tradis.Entites.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
