package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.abt.tradis.Entites.Pnom;

import java.util.List;
import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Pnom, Long> {

    Optional<Pnom> findByCnomAndCacc(String cnom, String cacc);

    Optional<Pnom> findByCnomAndLabel1(String cnom, String label1);

    Optional<Pnom> findByCnomAndLabel6(String cnom, String label6); // ✅ Pour un seul résultat

    List<Pnom> findAllByCnomAndLabel6(String cnom, String label6); // ✅ Pour une liste de résultats
}
