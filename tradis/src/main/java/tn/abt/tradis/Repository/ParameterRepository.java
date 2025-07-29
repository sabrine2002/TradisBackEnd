package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.abt.tradis.Entites.Pnom;

import java.util.List;
import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Pnom, String> {
    List<Pnom> findByCnom(String cnom);
    Optional<Pnom> findByCacc(String cacc);
    Optional<Pnom> findByCnomAndCacc(String cnom, String cacc);
    Optional<Pnom> findByCnomAndLabel3(String cnom, String label3);
    Optional<Pnom> findByCnomAndLabel4(String cnom, String label4);
    Optional<Pnom> findByCnomAndIdParam(String cnom, Long idParam);
    Optional<Pnom> findByIdParam(Long idParam);

}
