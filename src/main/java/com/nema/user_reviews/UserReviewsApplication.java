package com.nema.user_reviews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class UserReviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserReviewsApplication.class, args);
	}

}