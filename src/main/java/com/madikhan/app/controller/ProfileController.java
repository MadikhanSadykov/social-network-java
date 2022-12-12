package com.madikhan.app.controller;

import com.madikhan.app.model.Profile;
import com.madikhan.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("profiles", profileService.listAll());
        return "profiles";
    }

    @GetMapping("/{username}")
    public String show(@PathVariable("username") String username, Model model, HttpSession httpSession) {
        model.addAttribute("profile", profileService.listByUsername(username));

        Profile currentUser = (Profile) httpSession.getAttribute("profile");
        if (currentUser.getUsername().equals(username)){
            return "profiles/my-profile";
        }

        return "profiles/profile";
    }

    @PostMapping()
    public String createProfile(@ModelAttribute("profile") Profile profile) {
        profileService.save(profile);
        return "redirect:/profiles";
    }

}
