package com.goit.finalproject.security;

import com.goit.finalproject.role.RoleRepository;
import com.goit.finalproject.user.User;
import com.goit.finalproject.user.UserRepository;
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
       User user = userRepository.findUserByUsername(username)
                .orElse(null);

       if(user != null ) {
           return new RedirectView("/note/login");
       }

        user = User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .role(roleRepository.findRoleByRole("USER"))
                .build();

        userRepository.save(new User());

        return new RedirectView("/note/login");
    }

}
