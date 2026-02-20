package com.pulseforge.controller;

import com.pulseforge.model.WorkoutPlan;
import com.pulseforge.model.User;
import com.pulseforge.service.WorkoutService;
import com.pulseforge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserService userService;

    @GetMapping("/library")
    public ResponseEntity<?> getLibrary() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generatePlan(@RequestBody Map<String, Object> request) {
        String username = (String) request.get("username");
        String goal = (String) request.get("goal");
        String level = (String) request.get("level");
        int days = (int) request.get("days");

        return userService.findByUsername(username)
                .map(user -> ResponseEntity.ok(workoutService.generatePlan(user, goal, level, days)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/plan/{username}")
    public ResponseEntity<?> getPlan(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(user -> ResponseEntity.ok(workoutService.getUserPlan(user)))
                .orElse(ResponseEntity.notFound().build());
    }
}
