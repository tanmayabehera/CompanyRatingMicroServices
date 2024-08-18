package com.microservices.reviewservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.microservices.reviewservice.entity.ReviewEntity;

@Component
public interface ReviewService {
    public List<ReviewEntity> getAllReviews();

    public ReviewEntity addReview(ReviewEntity review);

    public void deleteReview(Long id);

    public Optional<ReviewEntity> getReviewById(Long id);

}
