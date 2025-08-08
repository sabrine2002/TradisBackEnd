package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.abt.tradis.Entites.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByIdCliAndFirstname(Long idCli, String firstname);
}
