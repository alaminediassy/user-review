package com.nema.user_reviews.controller;

import com.nema.user_reviews.entities.AppUser;
import com.nema.user_reviews.service.AppUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class AppUserController {

    private AppUserService appUserService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "inscription")
    public void inscription(@RequestBody AppUser appUser){
        log.info("inscription saved successfully");
        this.appUserService.inscription(appUser);
    }

    @PostMapping(path = "activation")
    public void inscription(@RequestBody Map<String, String> activation){
        this.appUserService.activation(activation);
    }
}
