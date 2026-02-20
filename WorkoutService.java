package com.pulseforge.service;

import com.pulseforge.model.Workout;
import com.pulseforge.model.WorkoutPlan;
import com.pulseforge.model.User;
import com.pulseforge.repository.WorkoutRepository;
import com.pulseforge.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public List<WorkoutPlan> generatePlan(User user, String goal, String level, int daysPerWeek) {
        // Simple logic to generate a plan based on goal and level
        List<Workout> allWorkouts = workoutRepository.findByDifficulty(level);
        if (allWorkouts.isEmpty()) {
            allWorkouts = workoutRepository.findAll();
        }

        Collections.shuffle(allWorkouts);
        List<WorkoutPlan> plan = new ArrayList<>();
        String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

        for (int i = 0; i < Math.min(daysPerWeek, allWorkouts.size()); i++) {
            WorkoutPlan wp = new WorkoutPlan();
            wp.setUser(user);
            wp.setWorkout(allWorkouts.get(i));
            wp.setDayOfWeek(days[i % days.length]);
            wp.setSets(3);
            wp.setReps(12);
            wp.setRestTime(60);
            plan.add(workoutPlanRepository.save(wp));
        }
        return plan;
    }

    public List<WorkoutPlan> getUserPlan(User user) {
        return workoutPlanRepository.findByUser(user);
    }
}
