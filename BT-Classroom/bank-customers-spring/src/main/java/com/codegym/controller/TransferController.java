package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.service.ICustomerService;
import com.codegym.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customers/transfer")
public class TransferController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransferService transferService;

    @GetMapping("/{id}")
    public ModelAndView transferForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("/transaction/transfer");
        Transfer transfer = new Transfer();
        transfer.setSender(customerService.findById(id).get());
        List<Customer> customers = (List<Customer>) customerService.findAll();
        customers.removeIf(customer -> customer.getId()==id);
        modelAndView.addObject("transfer", transfer);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView transfer(@Validated @ModelAttribute Transfer transfer, BindingResult bindingResult, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/transaction/transfer");
        Customer sender = customerService.findById(id).get();
        transfer.setSender(sender);
        List<Customer> customers = (List<Customer>) customerService.findAll();
        customers.removeIf(customer -> customer.getId()==id);
        modelAndView.addObject("customers", customers);
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("transfer", transfer);
            modelAndView.addObject("message", "error");
            modelAndView.addObject("errorMsg",bindingResult.getAllErrors());
            System.out.println(Arrays.toString(bindingResult.getAllErrors().get(0).getCodes()));
            return modelAndView;
        }

        if (transfer.getSender().getBalance()<transfer.getTransaction_amount()) {
            modelAndView.addObject("transfer", transfer);
            modelAndView.addObject("message", "error");
            modelAndView.addObject("outOfMoney","The sender's balance is not enough to make the transfer");
            return modelAndView;
        }
        sender.setBalance(sender.getBalance()-transfer.getTransaction_amount());
        Customer recipient = transfer.getRecipient();
        System.out.println(recipient.getEmail());
        recipient.setBalance(recipient.getBalance()+ transfer.getTransfer_amount());
        transfer.setRecipient(recipient);
        transfer.setFees(10);
        transfer.setFees_amount(transfer.getTransfer_amount()* transfer.getFees()/100);
        transfer.setId(0L);
        transferService.save(transfer);
        customerService.save(sender);
        customerService.save(recipient);
        modelAndView.addObject("message","success");
        modelAndView.addObject("transfer", transfer);
        return modelAndView;
    }

    @GetMapping("/information")
    public ModelAndView showTransferInformation() {
        ModelAndView modelAndView = new ModelAndView("/transaction/transfer-information");
        List<Transfer> transfers = (List<Transfer>) transferService.findAll();
        long totalFeesAmount = 0;
        for (Transfer transfer:
             transfers) {
            totalFeesAmount+= transfer.getFees_amount();
        }
        modelAndView.addObject("transfers",transfers);
        modelAndView.addObject("totalFeesAmount",totalFeesAmount);

        return modelAndView;
    }
}
