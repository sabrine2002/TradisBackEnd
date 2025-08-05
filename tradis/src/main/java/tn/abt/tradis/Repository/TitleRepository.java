package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.abt.tradis.Entites.Title;

import java.util.Optional;

public interface TitleRepository extends JpaRepository<Title, String> {
    @Query("SELECT COUNT(t) FROM Settlement t")
    long countAllTitles();
    Optional<Title> findByNumDom(String numDom);




}


