package com.goit.finalproject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class H2Controller {
    @Value("${spring.profiles.active}")
    private String actualProfile;

    @GetMapping("/")
    public ModelAndView getActiveProfile() {
        return "dev".equals(actualProfile)?
                new ModelAndView("redirect:/h2-console") :
                new ModelAndView("/test").addObject("profile",  actualProfile);
    }
}