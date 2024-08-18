package com.microservice.companyservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.companyservice.entity.CompanyEntity;
import com.microservice.companyservice.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyEntity> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping
    public CompanyEntity addJob(@RequestBody CompanyEntity company) {
        return companyService.addCompany(company);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }

    @GetMapping("/{id}")
    public Optional<CompanyEntity> getJobById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }
}
