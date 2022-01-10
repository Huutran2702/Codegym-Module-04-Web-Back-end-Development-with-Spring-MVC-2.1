package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.service.ICustomerService;
import com.codegym.service.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;


@Controller
@RequestMapping("/customers/deposit")
public class DepositController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;

    @GetMapping("/{id}")
    public ModelAndView depositForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/transaction/deposit");
        Deposit deposit = new Deposit();
        deposit.setCustomer(customerService.findById(id).get());
        modelAndView.addObject("deposit", deposit);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView deposit(@Validated  @ModelAttribute Deposit deposit, BindingResult bindingResult, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/transaction/deposit");
        Customer customer = customerService.findById(id).get();
        if (bindingResult.hasFieldErrors()) {
            deposit.setCustomer(customer);
            modelAndView.addObject("deposit",deposit);
            modelAndView.addObject("message","error");
            modelAndView.addObject("errorMsg",bindingResult.getAllErrors());
            return modelAndView;
        }
        customer.setBalance(customer.getBalance()+deposit.getTransaction_amount());
        customerService.save(customer);
        deposit.setCustomer(customer);
        deposit.setId(0L);
        depositService.save(deposit);
        modelAndView.addObject("message","success");
        modelAndView.addObject("deposit",deposit);
        return modelAndView;
    }

}
