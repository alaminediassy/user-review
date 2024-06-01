package com.nema.user_reviews.service;

import com.nema.user_reviews.entities.Validation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {

    JavaMailSender javaMailSender;
    public void sendNotification(Validation validation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hello@nema.sn");
        message.setTo(validation.getAppUser().getEmail());
        message.setSubject("Your activation code has been sent to " + validation.getAppUser().getEmail());

        String texte = String.format(
                "Bonjour %s, <br/> Votre code d'activation est %s; A bientôt",
                validation.getAppUser().getName(),
                validation.getCode());
        message.setText(texte);

        javaMailSender.send(message);
    }
}
