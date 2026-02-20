# PULSEFORGE - Setup Instructions

## Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+

## Database Setup
1. Open your MySQL client (e.g., MySQL Workbench or Command Line).
2. Execute the queries in `schema.sql` found in the root directory.
   ```sql
   SOURCE pulseforge/schema.sql;
   ```
3. Current configuration assumes:
   - **Host**: localhost:3306
   - **Username**: root
   - **Password**: (empty)
   *To change these, edit `src/main/resources/application.properties`.*

## Running the Application
1. Navigate to the `pulseforge` directory:
   ```bash
   cd pulseforge
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the Spring Boot app:
   ```bash
   mvn spring-boot:run
   ```
4. Access the website at: `http://localhost:8080`

## Project Structure
- `src/main/java/com/pulseforge/controller`: API Endpoints
- `src/main/java/com/pulseforge/model`: JPA Entities
- `src/main/java/com/pulseforge/service`: Business Logic
- `src/main/resources/static`: Frontend (HTML/CSS/JS)
- `src/main/resources/static/css/style.css`: Neon & Glassmorphism Design System
- `src/main/resources/static/js/api.js`: Frontend API Handlers
