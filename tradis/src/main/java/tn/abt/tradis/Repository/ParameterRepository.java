package tn.abt.tradis.Repository;

import tn.abt.tradis.Entites.Pnom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Pnom, Long> {
    Optional<Pnom> findByCacc(String cacc);

    Optional<Pnom> findByLabel6(String label6);

    Optional<Pnom> findByCnomAndLabel1(String cnom, String label1);  // Exemple : ("014", "USD")

    Optional<Pnom> findByCnomAndCacc(String cnom, String cacc);      // Exemple : ("014", "840")
    Optional<Pnom> findByCnomAndLabel3(String cnom, String label3);
    Optional<Pnom> findByCnomAndLabel2(String cnom, String label2);  // Ajouté : ("014", "USD")
}
