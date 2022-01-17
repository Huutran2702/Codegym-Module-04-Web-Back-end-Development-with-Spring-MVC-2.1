package com.codegym.controller.restful;


import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.service.ICustomerService;
import com.codegym.service.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customers/deposit")
public class DepositRestController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;

    @GetMapping("/{id}")
    public ResponseEntity<Deposit> getDepositByCustomerID(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        Deposit deposit = new Deposit();
        deposit.setCustomer(customerOptional.get());
        return new ResponseEntity<>(deposit, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Deposit> handlerDeposit(@Validated @RequestBody Deposit deposit,
                                                  BindingResult bindingResult,
                                                  @PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (bindingResult.hasFieldErrors()) {
            System.out.println("error");
        } else {
            optionalCustomer.get().setBalance(optionalCustomer.get().getBalance()+ deposit.getTransaction_amount());
            deposit.setCustomer(optionalCustomer.get());
            customerService.save(optionalCustomer.get());
            depositService.save(deposit);
        }
        return new ResponseEntity<>(deposit, HttpStatus.CREATED);
    }

}
