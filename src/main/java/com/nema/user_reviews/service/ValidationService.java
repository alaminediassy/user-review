package com.nema.user_reviews.service;

import com.nema.user_reviews.entities.AppUser;
import com.nema.user_reviews.entities.Validation;
import com.nema.user_reviews.repository.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@AllArgsConstructor
public class ValidationService {

    private ValidationRepository validationRepository;
    private NotificationService notificationService;

    public void saveValidation(AppUser appUser) {
        Validation validation = new Validation();
        validation.setAppUser(appUser);
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, MINUTES);
        validation.setExpiration(expiration);
        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);

        validation.setCode(code);
        this.validationRepository.save(validation);
        this.notificationService.sendNotification(validation);
    }

    public Validation readAboutCode(String code){
       return this.validationRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Votre code est invalide " + code));
    }
}
