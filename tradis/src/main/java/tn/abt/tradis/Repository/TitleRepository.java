package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.abt.tradis.Entites.Title;

public interface TitleRepository extends JpaRepository<Title, String> {
}