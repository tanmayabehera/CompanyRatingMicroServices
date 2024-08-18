package com.microservice.jobservice.mapper;

import java.util.List;

import com.microservice.jobservice.dto.JobDTO;
import com.microservice.jobservice.entity.JobEntity;
import com.microservice.jobservice.external.Company;
import com.microservice.jobservice.external.Review;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDTO( JobEntity job, Company company, List<Review> review){
        JobDTO jobWithCompanyDTO = new JobDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReviews(review);
        return jobWithCompanyDTO;
    }
}
