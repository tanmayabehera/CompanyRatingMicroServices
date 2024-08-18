package com.microservice.jobservice.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.microservice.jobservice.external.Company;
import com.microservice.jobservice.external.Review;
import lombok.Data;

@Component
@Data
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> reviews;
}
