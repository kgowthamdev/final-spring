package com.cts.controller;




import com.cts.entity.User;
import com.cts.service.ApplicationService;
import com.cts.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    @PostMapping("/jobs/{jobId}/apply")
    public ResponseEntity<?> applyForJob(@PathVariable Long jobId, Authentication authentication) {
        User candidate = userService.findByEmail(authentication.getName());
        return ResponseEntity.status(201).body(applicationService.applyForJob(candidate, jobId));
    }
}
