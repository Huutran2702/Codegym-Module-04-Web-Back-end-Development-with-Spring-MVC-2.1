package com.codegym.controller;

import com.codegym.model.EmailConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/configuration")
public class EmailController {
    @ModelAttribute("emailConfiguration")
    public EmailConfiguration getEmailConfiguration() {
        return new EmailConfiguration();
    }
    @GetMapping("/")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("/index");
        String[] listLanguages = new String[]{"English","Vietnamese","Japanese","Chinese"};
        long[] pageSizes = new long[]{5,10,15,25,50,100};
        modelAndView.addObject("listLanguages",listLanguages);
        modelAndView.addObject("pageSizes",pageSizes);
        return modelAndView;
    }

    @PostMapping("/show")
    public ModelAndView updateEmailConfiguration(@ModelAttribute EmailConfiguration emailConfiguration) {
        ModelAndView modelAndView = new ModelAndView("/show");
        modelAndView.addObject("emailConfiguration",emailConfiguration);

        return modelAndView;
    }
}
