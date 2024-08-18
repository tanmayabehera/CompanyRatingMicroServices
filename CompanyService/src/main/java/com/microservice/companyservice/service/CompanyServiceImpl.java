package com.microservice.companyservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservice.companyservice.clients.ReviewClient;
import com.microservice.companyservice.dto.ReviewMessage;
import com.microservice.companyservice.entity.CompanyEntity;
import com.microservice.companyservice.repository.CompanyRepository;
import jakarta.ws.rs.NotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<CompanyEntity> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public CompanyEntity addCompany(CompanyEntity company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Optional<CompanyEntity> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());
        CompanyEntity companyEntity = companyRepository.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new NotFoundException("Company Not Found"));
        double averageRating = reviewClient.getAverageRatingForCompany(companyEntity.getId());
        companyEntity.setRating(averageRating);
        companyRepository.save(companyEntity);
    }
}
