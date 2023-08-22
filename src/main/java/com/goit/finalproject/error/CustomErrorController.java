package com.goit.finalproject.error;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            modelAndView.setViewName("err404");
        }
        return modelAndView;
    }
}
