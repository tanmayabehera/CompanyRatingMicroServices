package com.microservices.reviewservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservices.reviewservice.entity.ReviewEntity;
import com.microservices.reviewservice.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewEntity> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public ReviewEntity addReview(ReviewEntity review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Optional<ReviewEntity> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
}
