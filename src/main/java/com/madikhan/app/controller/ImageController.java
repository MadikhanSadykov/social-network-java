package com.madikhan.app.controller;

import com.madikhan.app.model.Profile;
import com.madikhan.app.service.ImageService;
import com.madikhan.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/images")
public class ImageController {

    @Resource
    private final Environment environment;
    private final ProfileService profileService;
    private final ImageService imageService;

    @Autowired
    public ImageController(Environment environment, ProfileService profileService, ImageService imageService) {
        this.environment = environment;
        this.profileService = profileService;
        this.imageService = imageService;
    }



    @GetMapping("/{username}/avatar/edit")
    public String showUploadProfileAvatar(@PathVariable("username") String username, HttpSession httpSession) {
        Profile profile;
        Optional<Profile> optionalProfile = profileService.listByUsername(username);

        if (optionalProfile.isPresent()) {
            profile = optionalProfile.get();
        } else {
            return "redirect:/";
        }

        Long sessionProfileId = ((Profile) httpSession.getAttribute("profile")).getId();
        if (sessionProfileId.equals(profile.getId())) {
            return "profiles/profile-avatar-edit";
        }

        return "profiles/profile";
    }

    @PostMapping("/avatar/edit")
    public String uploadProfileAvatar(@RequestParam("image") MultipartFile image, HttpSession httpSession) {
        if(image.isEmpty()) {
            return "redirect:/profiles/profile";
        }

        String imageName = image.getOriginalFilename();
        if (imageName == null) {
            return "redirect:/profiles/profile";
        }

        Profile sessionProfile = (Profile) httpSession.getAttribute("profile");

        try {
            imageService.saveAvatar(image);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/profiles/profile";
        }

        profileService.saveAvatarById(sessionProfile.getId(), environment.getProperty("image.sub.dir") + imageName);
        sessionProfile.setImageUrl(environment.getProperty("image.sub.dir") + imageName);
        httpSession.setAttribute("profile", sessionProfile);

        return "redirect:/profiles/" + sessionProfile.getUsername();
    }

    @GetMapping("/{imageName}")
    public @ResponseBody byte[] getImage(@PathVariable(value = "imageName") String imageName) {
        byte[] imageBytes = new byte[0];

        try {
            imageBytes =  imageService.listImageByName(imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageBytes;
    }

}
