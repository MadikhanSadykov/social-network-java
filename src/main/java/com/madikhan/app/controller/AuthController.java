package com.madikhan.app.controller;

import com.madikhan.app.dto.ProfileDTO;
import com.madikhan.app.dto.UserDTO;
import com.madikhan.app.dto.mapper.ProfileDTOMapper;
import com.madikhan.app.dto.mapper.UserDTOMapper;
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
    private final UserDTOMapper userDTOMapper;
    private final ProfileDTOMapper profileDTOMapper;

    @Autowired
    public AuthController(UserService userService, ProfileService profileService, UserValidator userValidator,
                          ProfileValidator profileValidator, UserDTOMapper userDTOMapper, ProfileDTOMapper profileDTOMapper) {
        this.userService = userService;
        this.profileService = profileService;
        this.userValidator = userValidator;
        this.profileValidator = profileValidator;
        this.userDTOMapper = userDTOMapper;
        this.profileDTOMapper = profileDTOMapper;
    }

    @GetMapping("/register")
    public String registerPage(@ModelAttribute("user") User user, @ModelAttribute("profile") ProfileDTO profileDTO) {
        return "auth/register";
    }


    @PostMapping("/register")
    public String performRegister(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult userBindingResult,
                                  @ModelAttribute("profile") @Valid ProfileDTO profileDTO, BindingResult profileBindingResult) {
        profileValidator.validate(profileDTO, profileBindingResult);
        userValidator.validate(userDTO, userBindingResult);
        if (profileBindingResult.hasErrors() || userBindingResult.hasErrors()){
            return "auth/register";
        }

        User user = userDTOMapper.dtoToUser(userDTO);
        User persistedUser = userService.save(user);

        Profile profile = profileDTOMapper.dtoToProfile(profileDTO);
        profile.setUser(persistedUser);
        profileService.save(profile);

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

}