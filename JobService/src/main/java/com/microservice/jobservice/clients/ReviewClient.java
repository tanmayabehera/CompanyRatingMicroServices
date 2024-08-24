package com.microservice.jobservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.jobservice.external.Company;
import com.microservice.jobservice.external.Review;

@FeignClient(name = "REVIEWSERVICE", url = "{review-service.url}")
public interface ReviewClient {
    @GetMapping("/review")
    List<Review> getReviews();
}
