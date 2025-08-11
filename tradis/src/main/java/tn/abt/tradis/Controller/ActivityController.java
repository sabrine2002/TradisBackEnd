package tn.abt.tradis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Entites.Activity;
import tn.abt.tradis.Service.ActivityService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> getRecentActivities() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Activity> activities = activityService.getRecentActivitiesByUser(username);
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/activities")
    public ResponseEntity<Void> addActivity(@RequestBody Activity activity) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        activityService.addActivity(username, activity.getDescription(), activity.getType());
        return ResponseEntity.ok().build();
    }
}