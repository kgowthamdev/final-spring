package com.cts.service;


import com.cts.entity.Job;
import com.cts.entity.User;
import com.cts.repository.JobRepository;
import com.cts.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getJobsByEmployer(User employer) {
        return jobRepository.findByEmployerId(employer.getId());
    }

    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }
}

