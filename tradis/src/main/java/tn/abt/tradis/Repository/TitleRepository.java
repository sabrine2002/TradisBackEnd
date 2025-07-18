package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.abt.tradis.Entites.Title;

import java.util.Optional;

public interface TitleRepository extends JpaRepository<Title, String> {
    Optional<Title> findByNumDom(String numDom);
}
