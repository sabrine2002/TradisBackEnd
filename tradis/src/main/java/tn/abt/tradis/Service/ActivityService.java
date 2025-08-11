package tn.abt.tradis.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.abt.tradis.Entites.Activity;
import tn.abt.tradis.Repository.ActivityRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getRecentActivitiesByUser(String username) {
        return activityRepository.findTop5ByUsernameOrderByTimestampDesc(username);
    }

    public void addActivity(String username, String description, String type) {
        Activity activity = new Activity(username, description, type, LocalDateTime.now());
        activityRepository.save(activity);
    }
}