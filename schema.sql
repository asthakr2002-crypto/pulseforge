-- PULSEFORGE Database Schema

CREATE DATABASE IF NOT EXISTS pulseforge;
USE pulseforge;

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100),
    fitness_goal ENUM('Weight Loss', 'Muscle Gain', 'Endurance') DEFAULT 'Muscle Gain',
    experience_level ENUM('Beginner', 'Intermediate', 'Advanced') DEFAULT 'Beginner',
    weight FLOAT, -- in kg
    height FLOAT, -- in cm
    age INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Workouts Library (Predefined exercises)
CREATE TABLE IF NOT EXISTS workouts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL, -- Chest, Back, Legs, etc.
    difficulty ENUM('Beginner', 'Intermediate', 'Advanced') NOT NULL,
    description TEXT,
    instructions TEXT,
    image_url VARCHAR(255)
);

-- Saved Workout Plans for users
CREATE TABLE IF NOT EXISTS workout_plans (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    workout_id INT NOT NULL,
    day_of_week VARCHAR(20), -- Monday, Tuesday, etc.
    sets INT DEFAULT 3,
    reps INT DEFAULT 12,
    rest_time INT DEFAULT 60, -- in seconds
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (workout_id) REFERENCES workouts(id) ON DELETE CASCADE
);

-- Progress Logs
CREATE TABLE IF NOT EXISTS progress_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    date DATE NOT NULL,
    calories_burned INT DEFAULT 0,
    weight_kg FLOAT, -- Body weight tracking
    notes TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Sample Data
INSERT INTO workouts (name, category, difficulty, description, instructions) VALUES
('Bench Press', 'Chest', 'Intermediate', 'A classic upper body exercise.', 'Lay on bench, lower bar to chest, push up.'),
('Squats', 'Legs', 'Beginner', 'The King of all exercises.', 'Feet shoulder width apart, lower hips, stand back up.'),
('Deadlift', 'Back', 'Advanced', 'Total body strength builder.', 'Grip bar, keep back straight, lift using legs and hips.'),
('Pushups', 'Chest', 'Beginner', 'Bodyweight essential.', 'Keep core tight, lower chest to floor, push back up.'),
('Pullups', 'Back', 'Intermediate', 'Upper body pulling strength.', 'Hang from bar, pull chin over bar, lower slowly.');
