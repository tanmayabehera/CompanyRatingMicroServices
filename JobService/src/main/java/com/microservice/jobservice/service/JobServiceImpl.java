package com.microservice.jobservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.jobservice.clients.CompanyClient;
import com.microservice.jobservice.clients.ReviewClient;
import com.microservice.jobservice.dto.JobDTO;
import com.microservice.jobservice.entity.JobEntity;
import com.microservice.jobservice.external.Company;
import com.microservice.jobservice.external.Review;
import com.microservice.jobservice.mapper.JobMapper;
import com.microservice.jobservice.repository.JobRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class JobServiceImpl implements JobService{

    private final JobRepository jobRepository;
    private final RestTemplate restTemplate;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    public JobServiceImpl(JobRepository jobRepository, RestTemplate restTemplate, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.restTemplate = restTemplate;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<JobEntity> getAllJobs() {

        return jobRepository.findAll();
    }

    private JobDTO convertToDto(JobEntity job) {
//        Company company = restTemplate.getForObject("http://COMPANYSERVICE:1081/company/" + job.getCompanyId(), Company.class);
        Company company = companyClient.getCompany(job.getCompanyId());
//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
//                "http://REVIEWSERVICE:8082/review",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>(){
//
//                }
//        );
//        List<Review> reviews = reviewResponse.getBody();
        List<Review> reviews = reviewClient.getReviews();
        return JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
    }

    @Override
    public JobEntity addJob(JobEntity job) {
       return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public JobDTO getJobById(Long id) {
        JobEntity job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);
    }

    public JobDTO companyBreakerFallback(Long id, Exception e) {

        return new JobDTO();
    }
}
