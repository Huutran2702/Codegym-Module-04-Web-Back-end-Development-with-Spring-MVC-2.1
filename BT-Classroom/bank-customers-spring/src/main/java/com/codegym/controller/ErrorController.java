package com.codegym.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller("/errors")
public class ErrorController {
    @GetMapping("/errors")
    public ModelAndView renderErrorPage(HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error-page");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpServletRequest);
        System.out.println(httpErrorCode);
        switch (httpErrorCode){
            case 400:
                errorMsg = "Loi 400";
                break;
            case 404:
                errorMsg = "Loi 404";
                break;
        }
        modelAndView.addObject("errorMsg",errorMsg);
        return modelAndView;
    }

    @PostMapping("/errors")
    public ModelAndView renderError(HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error-page");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpServletRequest);
        System.out.println(httpErrorCode);
        switch (httpErrorCode){
            case 400:
                errorMsg = "Loi 400";
                break;
            case 500:
                errorMsg = "Loi 500";
                break;
        }
        modelAndView.addObject("errorMsg",errorMsg);
        return modelAndView;
    }

    private int getErrorCode(HttpServletRequest httpServletRequest){
        return (Integer) httpServletRequest.getAttribute("javax.servlet.error.status_code");
    }
}
