package com.goit.finalproject.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@Secured("ADMIN")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/users")
    public ModelAndView showAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
        return new ModelAndView()
                .addObject("users", userRepository.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/user/addUser")
    public ModelAndView getAddUserPage() {
        return new ModelAndView("add-user").addObject("user", new User());
    }

    @PostMapping("/user/addUser")
    public ModelAndView addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("add-user");
        }

        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            return new ModelAndView("add-user").addObject("error", true);
        }

        userService.createUser(user);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/user/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Long id) {
        return new ModelAndView("edit-user").addObject("user", userRepository.getReferenceById(id));
    }

    @PostMapping("/user/edit/{id}")
    public ModelAndView updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("edit-user");
        }

        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            return new ModelAndView("edit-user").addObject("error", true);
        }

        userService.updateUser(user);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

}
