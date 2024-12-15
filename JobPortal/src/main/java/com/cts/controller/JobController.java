package com.cts.controller;

import com.cts.entity.Job;
import com.cts.entity.User;
import com.cts.service.JobService;
import com.cts.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody Job job, Authentication authentication) {
        // Fetch logged-in user
        User employer = userService.findByEmail(authentication.getName());

        // Check if the logged-in user is an EMPLOYER
        if (!"EMPLOYER".equalsIgnoreCase(employer.getRole())) {
            return ResponseEntity.status(403).body("Only employers can post jobs.");
        }

        // Assign employer to job and save
        job.setEmployer(employer);
        return ResponseEntity.status(201).body(jobService.createJob(job));
    }
}

