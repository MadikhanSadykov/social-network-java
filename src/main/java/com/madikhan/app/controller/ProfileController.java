package com.madikhan.app.controller;

import com.madikhan.app.model.Profile;
import com.madikhan.app.security.AccountDetails;
import com.madikhan.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("profiles", profileService.listAll());
        return "profiles";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("profile", profileService.listById(id));
        return "profile";
    }

    @GetMapping("/new")
    public String newProfile(Model model) {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Long userId = accountDetails.getUser().getId();
        Profile profile = new Profile();
        profile.setUserId(userId);
        model.addAttribute("profile", profile);
        return "profile-new";
    }

    @PostMapping()
    public String createProfile(@ModelAttribute("profile") Profile profile) {
        profileService.save(profile);
        return "redirect:/profiles";
    }

}
