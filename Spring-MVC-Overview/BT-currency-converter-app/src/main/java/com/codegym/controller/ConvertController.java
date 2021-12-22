package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ConvertController  {

    @GetMapping("/index")
    public String show() {
        return "index";
    }

   @PostMapping("/convert")
    public ModelAndView convert(@RequestParam double rate, double usd) {
        ModelAndView modelAndView = new ModelAndView("convert");
        double vnd = rate*usd;
        modelAndView.addObject("vnd", vnd);
        modelAndView.addObject("rate",rate);
        modelAndView.addObject("usd", usd);
        return modelAndView;
    }
}
