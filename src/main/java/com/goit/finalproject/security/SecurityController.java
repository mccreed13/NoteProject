package com.goit.finalproject.security;

import com.goit.finalproject.role.RoleRepository;
import com.goit.finalproject.user.User;
import com.goit.finalproject.user.UserRepository;
import com.goit.finalproject.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class SecurityController implements WebMvcConfigurer {
    private final UserRepository userRepository;
    private final UserService userService;


//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/register").setViewName("ownReg");
//    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("/login");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(@ModelAttribute User user) {
        return new ModelAndView("/ownReg");
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult) {
        //TODO потрібно додати повідомлення що юзер за таким ім'ям існує
        //TODO якщо паролі не співпадають робити редірект на /register

        if(bindingResult.hasErrors()) {
            return "redirect:/register";
        }

        if(userRepository.findUserByUsername(user.getUsername()) != null ) {
            return "redirect:/note/login";
       }

//        user = User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .build();

        userService.createUser(user);
        userRepository.save(user);

        return "redirect:/note/list";
    }


}
