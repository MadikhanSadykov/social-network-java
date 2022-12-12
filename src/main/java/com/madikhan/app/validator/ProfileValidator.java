package com.madikhan.app.validator;

import com.madikhan.app.model.Profile;
import com.madikhan.app.service.ProfileService;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProfileValidator implements Validator {

    private final ProfileService profileService;

    public ProfileValidator(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Profile.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Profile profile = (Profile) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");
        if (profile.getUsername().length() < 6 || profile.getUsername().length() > 32) {
            errors.rejectValue("username", "size.username.email", "Username must be between 6 and 32 characters");
        }

        if (profileService.listByUsername(profile.getUsername()).isPresent()) {
            errors.rejectValue("username", "duplicate.username.email", "Username already exists");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required");
        if (profile.getFirstName().length() < 2 || profile.getFirstName().length() > 16) {
            errors.rejectValue("firstName", "size.first.name.email", "First name must be between 2 and 16 characters");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required");
        if (profile.getLastName().length() < 2 || profile.getLastName().length() > 16) {
            errors.rejectValue("firstName", "size.last.name.email", "Last name  must be between 2 and 16 characters");
        }

    }
}
