package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class LoginController {

    @GetMapping("")
    public ModelAndView loginForm() {
        ModelAndView modelAndView = new ModelAndView("/login");
        User user = new User();
        modelAndView.addObject("user",user);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message",new Locale("haw"));
        modelAndView.addObject("resourceBundle",resourceBundle);
        return modelAndView;
    }

    @PostMapping("/doLogin")
    public ModelAndView processLogin(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (user.getUsername().equals("admin")) {
            if (user.getPassword().equals("123123")) {
                modelAndView.setViewName("/dashboard");
                modelAndView.addObject("user",user);
                return modelAndView;
            }
        }
        modelAndView.setViewName("/login");
        return modelAndView;
    }
}
