package com.codegym.controller;


import com.codegym.model.Customer;
import com.codegym.model.Withdraw;
import com.codegym.service.ICustomerService;
import com.codegym.service.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Objects;

@Controller
@RequestMapping("/customers/withdraw")
public class WithdrawController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IWithdrawService withdrawService;

    @GetMapping("/{id}")
    public ModelAndView withdrawForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/transaction/withdraw");
        Withdraw withdraw = new Withdraw();
        withdraw.setCustomer(customerService.findById(id).get());
        modelAndView.addObject("withdraw", withdraw);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView deposit(@Validated @ModelAttribute Withdraw withdraw, BindingResult bindingResult, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/transaction/withdraw");
        if (bindingResult.hasFieldErrors()) {
            addObjectToViewIfError(withdraw, id, modelAndView);
            modelAndView.addObject("errorMsg",bindingResult.getAllErrors());
            return modelAndView;
        }
        Customer customer = customerService.findById(id).get();
        if (withdraw.getTransaction_amount()>customer.getBalance()) {
            addObjectToViewIfError(withdraw, id, modelAndView);
            modelAndView.addObject("outOfMoney","Customer's balance is not enough to make a withdrawal");
            return modelAndView;
        }

        customer.setBalance(customer.getBalance()-withdraw.getTransaction_amount());
        customerService.save(customer);
        withdraw.setCustomer(customer);
        withdraw.setId(0L);
        withdrawService.save(withdraw);
        modelAndView.addObject("message","success");
        modelAndView.addObject("withdraw",withdraw);
        return modelAndView;
    }

    private void addObjectToViewIfError(@ModelAttribute @Validated Withdraw withdraw, @PathVariable Long id, ModelAndView modelAndView) {
        withdraw.setCustomer(customerService.findById(id).get());
        modelAndView.addObject("withdraw", withdraw);
        modelAndView.addObject("message", "error");
    }
}
