package com.pulseforge.repository;

import com.pulseforge.model.ProgressLog;
import com.pulseforge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProgressLogRepository extends JpaRepository<ProgressLog, Long> {
    List<ProgressLog> findByUserOrderByDateDesc(User user);
}
