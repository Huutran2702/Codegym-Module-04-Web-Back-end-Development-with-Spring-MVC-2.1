package com.codegym.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class SandwichController {

    @GetMapping()
    public ModelAndView showList() {
        return new ModelAndView("index");
    }

    @RequestMapping("/save")
    public String save(@RequestParam("condiment") String[] condiment) {
          return "show";
    }
}


