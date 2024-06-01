package com.nema.user_reviews.repository;

import com.nema.user_reviews.entities.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);
}
