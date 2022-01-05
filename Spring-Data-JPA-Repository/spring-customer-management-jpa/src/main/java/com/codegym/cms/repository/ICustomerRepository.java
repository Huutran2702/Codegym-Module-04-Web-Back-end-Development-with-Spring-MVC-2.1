package com.codegym.cms.repository;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    Iterable<Customer> findAllByProvince(Province province);

    Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable);

    boolean existsCustomerByPhoneNumber(String phone);

}
