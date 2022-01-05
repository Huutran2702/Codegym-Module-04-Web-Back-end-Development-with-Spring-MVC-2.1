package com.codegym.controller;

import com.codegym.exception.DivideByZeroException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String calculator() {
        return "/calculator";
    }

//    @PostMapping("/calc")
//    public ModelAndView getResult(@RequestParam("num1") long num1, @RequestParam("num2") long num2){
//        ModelAndView modelAndView = new ModelAndView();
//        try {
//            long result = num1 / num2;
//            modelAndView.setViewName("/calculator");
//           modelAndView.addObject("result", result);
//      } catch (DivideByZeroException e){
//            modelAndView.setViewName("/error500");
//        }
//        return modelAndView;
//    }

//    @PostMapping("/calc")
//    public ModelAndView getResult(@RequestParam("num1") long num1, @RequestParam("num2") long num2) throws DivideByZeroException {
//        ModelAndView modelAndView = new ModelAndView();
//        try {
//            long result = num1 / num2;
//            modelAndView.setViewName("/calculator");
//            modelAndView.addObject("result", result);
//        } catch (Exception e){
//            throw new DivideByZeroException();
//        }
//        return modelAndView;
//    }

    @PostMapping("/calc")
    public ModelAndView getResult(@RequestParam("num1") long num1, @RequestParam("num2") long num2) {
        ModelAndView modelAndView = new ModelAndView();
        long result = num1 / num2;
        modelAndView.setViewName("/calculator");
        modelAndView.addObject("result", result);
        return modelAndView;
    }

//    @ExceptionHandler(DivideByZeroException.class)
//    public ModelAndView showInputNotAcceptable() {
//        System.out.println("avc");
//        return new ModelAndView("error-page");
//    }
}
