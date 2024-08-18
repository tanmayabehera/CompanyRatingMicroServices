package com.microservice.jobservice.external;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Company {
    private Long id;
    private String title;
    private String location;

}
