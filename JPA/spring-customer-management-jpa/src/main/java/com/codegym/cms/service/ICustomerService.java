package com.codegym.cms.service;

import com.codegym.cms.model.Customer;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
    List<Customer> searchByAll(String keySearch);
}
