package com.microservice.jobservice.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.microservice.jobservice.dto.JobDTO;
import com.microservice.jobservice.entity.JobEntity;

@Component
public interface JobService {
    public List<JobEntity> getAllJobs();

    public JobEntity addJob(JobEntity job);

    public void deleteJob(Long id);

    public JobDTO getJobById(Long id);

}
