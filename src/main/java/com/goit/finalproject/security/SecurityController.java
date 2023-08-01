package com.goit.finalproject.security;

import com.goit.finalproject.user.User;
import com.goit.finalproject.user.UserRepository;
import com.goit.finalproject.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("/login");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("register").addObject("user", new User());
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "/register";
        }
        //TODO вивести повідомлення
        if(userRepository.findUserByUsername(user.getUsername()) != null) {
            return "redirect:/login";
        }

        userService.createUser(user);
        userRepository.save(user);
        return "redirect:/note/list";
    }

}
