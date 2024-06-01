package com.nema.user_reviews.service;

import com.nema.user_reviews.entities.Review;
import com.nema.user_reviews.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void saveReview(Review review) {
        this.reviewRepository.save(review);
    }
}
