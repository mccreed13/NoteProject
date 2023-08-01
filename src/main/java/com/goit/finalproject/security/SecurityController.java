package com.goit.finalproject.security;

import com.goit.finalproject.role.RoleRepository;
import com.goit.finalproject.user.User;
import com.goit.finalproject.user.UserRepository;
import com.goit.finalproject.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("/login");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("/ownReg");
    }

    @PostMapping("/register")
    public RedirectView registerUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ) {
        //TODO потрібно додати повідомлення що юзер за таким ім'ям існує
        //TODO якщо паролі не співпадають робити редірект на /register
       if(userRepository.findUserByUsername(username) != null ) {
           return new RedirectView("/login");
       }

       User user = User.builder()
                .username(username)
                .password(password)
                .build();

        userService.createUser(user);
        userRepository.save(user);

        return new RedirectView("/login");
    }
    
    @GetMapping("/")
    public String getDefaultPage() {
        return "redirect:/note/list";
    }
}
