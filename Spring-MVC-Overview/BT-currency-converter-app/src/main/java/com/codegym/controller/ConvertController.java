package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ConvertController  {

    @RequestMapping(value = { "/index"}, method = RequestMethod.GET)
    public String show() {
        return "index";
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public ModelAndView convert(@RequestParam double rate, double usd) {
        ModelAndView modelAndView = new ModelAndView("convert");
        double vnd = rate*usd;
        modelAndView.addObject("vnd", vnd);
        modelAndView.addObject("rate",rate);
        modelAndView.addObject("usd", usd);
        return modelAndView;
    }
}
