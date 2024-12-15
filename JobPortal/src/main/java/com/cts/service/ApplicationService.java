package com.cts.service;


import com.cts.entity.Application;
import com.cts.entity.Job;
import com.cts.entity.User;

import com.cts.repository.ApplicationRepository;
import com.cts.repository.JobRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    public Application applyForJob(User candidate, Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        Application application = new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus("PENDING");
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsForJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    public List<Application> getApplicationsByCandidate(User candidate) {
        return applicationRepository.findByCandidateId(candidate.getId());
    }

    public Application updateApplicationStatus(Long applicationId, String status) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        return applicationRepository.save(application);
    }
}

   