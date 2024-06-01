package com.nema.user_reviews.repository;

import com.nema.user_reviews.entities.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
