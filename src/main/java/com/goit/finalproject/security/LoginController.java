package com.goit.finalproject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    @GetMapping
    public ModelAndView getLoginPage() {
        return new ModelAndView("/login");
    }

}
