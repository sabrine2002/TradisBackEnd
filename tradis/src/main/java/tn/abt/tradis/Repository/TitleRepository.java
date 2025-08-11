package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.abt.tradis.Entites.Title;

import java.util.List;
import java.util.Optional;

public interface TitleRepository extends JpaRepository<Title, String> {
    @Query("SELECT COUNT(t) FROM Settlement t")
    long countAllTitles();
    Optional<Title> findByNumDom(String numDom);
    List<Title> findByUserId(Long userId);
    Long countByUserId(Long userId);

    @Query("SELECT COUNT(t) FROM Title t WHERE t.user.id = :utilisateurId AND t.titleStatus.idParam = :statusCode")
    Long countTitlesByUserIdAndStatusCode(Long utilisateurId, Long statusCode);
}


