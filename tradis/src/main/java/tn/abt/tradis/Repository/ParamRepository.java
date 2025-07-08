package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.abt.tradis.Entites.Pnom;

import java.util.Optional;

public interface ParamRepository extends JpaRepository<Pnom, Long> {
    Optional<Pnom> findByCacc(String cacc);
    boolean existsByCacc(String cacc);
    Optional<Pnom> findByCnom(String cnom);
}
