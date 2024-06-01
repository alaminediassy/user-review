package com.nema.user_reviews.repository;

import com.nema.user_reviews.entities.Validation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation, Integer> {
    Optional<Validation> findByCode(String code);
}
