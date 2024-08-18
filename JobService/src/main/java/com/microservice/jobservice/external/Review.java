package com.microservice.jobservice.external;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Review {
    private Long id;
    private String title;
    private String description;
    private double rating;
}
