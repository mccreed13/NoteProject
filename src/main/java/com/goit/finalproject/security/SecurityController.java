package com.goit.finalproject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("/login");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("/registration");
    }

    @GetMapping("/")
    public String getPage() {
        return "redirect:/note/list";
    }

//    @PostMapping("/register")
//    public


}
