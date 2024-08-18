package com.microservice.companyservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.microservice.companyservice.dto.ReviewMessage;
import com.microservice.companyservice.entity.CompanyEntity;

@Component
public interface CompanyService {
    public List<CompanyEntity> getAllCompanies();

    public CompanyEntity addCompany(CompanyEntity company);

    public void deleteCompany(Long id);

    public Optional<CompanyEntity> getCompanyById(Long id);

    public void updateCompanyRating(ReviewMessage reviewMessage);
}
