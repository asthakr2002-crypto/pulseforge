package com.pulseforge.repository;

import com.pulseforge.model.WorkoutPlan;
import com.pulseforge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
    List<WorkoutPlan> findByUser(User user);

    List<WorkoutPlan> findByUserAndDayOfWeek(User user, String dayOfWeek);
}
