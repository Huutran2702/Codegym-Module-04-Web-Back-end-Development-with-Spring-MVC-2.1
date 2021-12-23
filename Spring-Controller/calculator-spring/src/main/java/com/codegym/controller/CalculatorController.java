package com.codegym.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class CalculatorController {

    @GetMapping()
    public ModelAndView showList() {
        return new ModelAndView("index");
    }

    @RequestMapping("/save")
    public String save(@RequestParam("submit") String submit,
                       @RequestParam("operator1") double ope1,
                       @RequestParam("operator2") double ope2,
                       Model model
                       ) {
        double result = 0;
       switch (submit) {
           case "Addition(+)":
               result = ope1+ope2;
               break;
           case "Subtraction(-)":
               result = ope1-ope2;
               break;
           case "Multiplication(x)":
               result = ope1*ope2;
               break;
           case "Division(/)":
               result= ope1/ope2;
               break;
       }
        model.addAttribute("operator",submit.substring(0,submit.indexOf("(")));
        model.addAttribute("result",result);
          return "index";
    }
}


