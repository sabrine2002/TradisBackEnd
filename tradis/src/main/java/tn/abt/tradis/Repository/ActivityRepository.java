package tn.abt.tradis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.abt.tradis.Entites.Activity;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findTop5ByUsernameOrderByTimestampDesc(String username);
}