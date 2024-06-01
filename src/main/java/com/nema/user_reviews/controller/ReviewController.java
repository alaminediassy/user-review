package com.nema.user_reviews.controller;

import com.nema.user_reviews.entities.Review;
import com.nema.user_reviews.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveReview(@RequestBody Review review) {
        reviewService.saveReview(review);
    }
}
