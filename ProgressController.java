package com.pulseforge.controller;

import com.pulseforge.model.ProgressLog;
import com.pulseforge.model.User;
import com.pulseforge.service.ProgressService;
import com.pulseforge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @Autowired
    private UserService userService;

    @PostMapping("/log")
    public ResponseEntity<?> logProgress(@RequestBody ProgressLog log, @RequestParam String username) {
        return userService.findByUsername(username)
                .map(user -> {
                    log.setUser(user);
                    return ResponseEntity.ok(progressService.saveLog(log));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getLogs(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(user -> ResponseEntity.ok(progressService.getUserLogs(user)))
                .orElse(ResponseEntity.notFound().build());
    }
}
