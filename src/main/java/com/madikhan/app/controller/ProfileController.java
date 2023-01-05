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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Optional;


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

        Profile currentUser = (Profile) httpSession.getAttribute("profile");
        if (currentUser.getUsername().equals(username)){
            return "profiles/my-profile";
        }

        Optional<Profile> optionalProfile = profileService.listByUsername(username);
        if (optionalProfile.isPresent()) {
            model.addAttribute("profile", optionalProfile.get());
            return "profiles/profile";
        }

        return "redirect:/";
    }

    @PostMapping()
    public String createProfile(@ModelAttribute("profile") Profile profile) {
        profileService.save(profile);
        return "redirect:/profiles";
    }

    @GetMapping("/{username}/edit")
    public String showEditProfile(@PathVariable("username") String username, HttpSession httpSession) {
        Profile profile;
        Optional<Profile> optionalProfile = profileService.listByUsername(username);

        if (optionalProfile.isPresent()) {
            profile = optionalProfile.get();
        } else {
            return "redirect:/";
        }

        Long sessionProfileId = ((Profile) httpSession.getAttribute("profile")).getId();
        if (sessionProfileId.equals(profile.getId())) {
            return "profiles/profile-edit";
        }

        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editProfile(@RequestParam("id") Long id,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("username") String username,
                              @RequestParam(value = "bio", required = false) String bio,
                              @RequestParam(value = "birthDate", required = false) Date birthDate,
                              @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                              HttpSession httpSession) {
        Profile profile;
        Profile sessionProfile = (Profile) httpSession.getAttribute("profile");
        Long sessionProfileId = sessionProfile.getId();

        if (id.equals(sessionProfileId)) {
            profile = new Profile();
            profile.setId(id);
            profile.setFirstName(firstName);
            profile.setLastName(lastName);
            profile.setUsername(username);
            profile.setBio(bio);
            profile.setBirthDate(birthDate);
            profile.setPhoneNumber(phoneNumber);
            profile.setCreatedDate(sessionProfile.getCreatedDate());
            profile.setUser(sessionProfile.getUser());
            profile.setImageUrl(sessionProfile.getImageUrl());
            Profile persistedProfile = profileService.update(profile);
            httpSession.setAttribute("profile", persistedProfile);
        }

        return "redirect:/profiles/" + sessionProfile.getUsername();
    }

}
