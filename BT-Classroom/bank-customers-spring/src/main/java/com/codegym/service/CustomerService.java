package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;

import java.util.Optional;

public class CustomerService implements ICustomerService{

    private ICustomerRepository customerRepository;
    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer saveAndGet(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }
}
