package com.madikhan.app.controller;

import com.madikhan.app.model.Profile;
import com.madikhan.app.model.User;
import com.madikhan.app.service.ProfileService;
import com.madikhan.app.service.UserService;
import com.madikhan.app.validator.ProfileValidator;
import com.madikhan.app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final ProfileService profileService;
    private final UserValidator userValidator;
    private final ProfileValidator profileValidator;

    @Autowired
    public AuthController(UserService userService, ProfileService profileService, UserValidator userValidator, ProfileValidator profileValidator) {
        this.userService = userService;
        this.profileService = profileService;
        this.userValidator = userValidator;
        this.profileValidator = profileValidator;
    }

    @GetMapping("/register")
    public String registerPage(@ModelAttribute("user") User user, @ModelAttribute("profile") Profile profile) {
        return "auth/register";
    }


    @PostMapping("/register")
    public String performRegister(@ModelAttribute("user") @Valid User user, BindingResult userBindingResult,
                                  @ModelAttribute("profile") @Valid Profile profile, BindingResult profileBindingResult) {
        profileValidator.validate(profile, profileBindingResult);
        userValidator.validate(user, userBindingResult);
        if (profileBindingResult.hasErrors() || userBindingResult.hasErrors()){
            return "auth/register";
        }

        User persistedUser = userService.save(user);
        profile.setUserId(persistedUser.getId());
        profileService.save(profile);

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

}
