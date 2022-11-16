package com.madikhan.app.validator;

import com.madikhan.app.model.User;
import com.madikhan.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("email", "size.login.email", "Email must be between 8 and 64 characters");
        }

        if (userService.listByUsername(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "duplicate.login.email", "This email is already in usel");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "size.login.password", "Password must be over 8 characters");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "different.login.password", "Password doesn't match");
        }

    }
}
