package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        Iterable<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCustomerForm(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer.get());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@Validated @ModelAttribute("customer")  Customer customer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        if (bindingResult.hasFieldErrors()) {
            System.out.println(bindingResult);
            modelAndView.addObject("customer", customer);
            return modelAndView;
        } else {
            customerService.save(customerService.convert(customer));
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("message", "success");
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createCustomerForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(@Validated @ModelAttribute Customer customer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer",customer);
        List<Customer> customers = (List<Customer>) customerService.findAll();
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("customer", customer);
            return modelAndView;
        }
        for (Customer c: customers) {
            if (customer.getEmail().equals(c.getEmail())) {
                modelAndView.addObject("message","exist");
                return modelAndView;
            }
        }
        customerService.save(customer);
        modelAndView.addObject("message","success");
        return modelAndView;
    }

    @GetMapping("/suspended/{id}")
    public ModelAndView deleteCustomerForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/customer/delete");
        Optional<Customer> customer = customerService.findById(id);
        modelAndView.addObject("customer", customer.get());
        return modelAndView;
    }

    @PostMapping("/suspended/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/customer/delete");
        Customer customer = customerService.findById(id).get();
        customer.setDeleted(1);
        customerService.save(customer);
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message","success");
        return modelAndView;
    }
}
