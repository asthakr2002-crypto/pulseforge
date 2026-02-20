package com.pulseforge.service;

import com.pulseforge.model.ProgressLog;
import com.pulseforge.model.User;
import com.pulseforge.repository.ProgressLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressLogRepository progressLogRepository;

    public ProgressLog saveLog(ProgressLog log) {
        return progressLogRepository.save(log);
    }

    public List<ProgressLog> getUserLogs(User user) {
        return progressLogRepository.findByUserOrderByDateDesc(user);
    }
}
