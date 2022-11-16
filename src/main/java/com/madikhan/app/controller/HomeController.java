package com.madikhan.app.controller;

import com.madikhan.app.security.AccountDetails;
import com.madikhan.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public String index() {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Long userId = accountDetails.getUser().getId();
        if (profileService.listByUserId(userId).getUsername() == null) {
            return "redirect:/profiles/new";
        }
        return "index";
    }


}
