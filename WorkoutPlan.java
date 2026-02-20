package com.pulseforge.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "workout_plans")
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    private String dayOfWeek;
    private Integer sets;
    private Integer reps;
    private Integer restTime;
}
