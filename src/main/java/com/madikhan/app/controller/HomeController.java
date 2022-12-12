package com.madikhan.app.controller;

import com.madikhan.app.model.Profile;
import com.madikhan.app.security.AccountDetails;
import com.madikhan.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ProfileService profileService;

    @Autowired
    public HomeController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String index(HttpSession session) {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Long userId = accountDetails.getUser().getId();
        Profile profile = profileService.listByUserId(userId);

        if (ObjectUtils.isEmpty(profile)) {
            return "redirect:/profiles/new";
        }

        session.setAttribute("profile", profile);

        return "index";
    }


}
