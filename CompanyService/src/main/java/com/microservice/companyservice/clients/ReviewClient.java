package com.microservice.companyservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("REVIEWSERVICE")
public interface ReviewClient {
    @GetMapping("/review/averageRating")
    Double getAverageRatingForCompany(@RequestParam("companyId") Long companyId);
}
