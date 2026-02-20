package com.pulseforge.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String difficulty;
    private String description;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    private String imageUrl;
}
