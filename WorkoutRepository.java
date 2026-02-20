package com.pulseforge.repository;

import com.pulseforge.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByCategory(String category);

    List<Workout> findByDifficulty(String difficulty);
}
