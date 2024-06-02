package com.nema.user_reviews.service;

import com.nema.user_reviews.entities.AppUser;
import com.nema.user_reviews.entities.Role;
import com.nema.user_reviews.entities.Validation;
import com.nema.user_reviews.enums.RoleType;
import com.nema.user_reviews.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {
    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ValidationService validationService;

    public void inscription(AppUser appUser) {
        if (!appUser.getEmail().contains("@")){
            throw new RuntimeException("Invalid email address");
        }
        if (!appUser.getEmail().contains(".")){
            throw new RuntimeException("Invalid email address");
        }

        Optional<AppUser> optionalAppUser = this.appUserRepository.findByEmail(appUser.getEmail());
        if (optionalAppUser.isPresent()) {
            throw new RuntimeException("This email address is already in use");
        }

        String cryptPassword = this.bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(cryptPassword);

        Role roleUser = new Role();
        roleUser.setWording(RoleType.USER);
        appUser.setRole(roleUser);

        appUser = this.appUserRepository.save(appUser);
        this.validationService.saveValidation(appUser);
    }

    public void activation(Map<String, String> activation) {
        Validation validation = this.validationService.readAboutCode(activation.get("code"));
        if (Instant.now().isAfter(validation.getExpiration())){
            throw new RuntimeException("Votre code a expiré");
        }
        AppUser appUserActivate = this.appUserRepository.findById(validation.getAppUser().getId()).orElseThrow(() ->
                new RuntimeException("Utilisateur inconnu"));
        appUserActivate.setEnabled(true);
        this.appUserRepository.save(appUserActivate);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.appUserRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
