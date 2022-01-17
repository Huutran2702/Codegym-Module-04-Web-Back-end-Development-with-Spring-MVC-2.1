package com.codegym.service;

import com.codegym.model.Deposit;
import com.codegym.repository.IDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService implements IDepositService{

    @Autowired
    private IDepositRepository depositRepository;
    @Override
    public Iterable<Deposit> findAll() {
        return depositRepository.findAll();
    }

    @Override
    public Optional<Deposit> findById(Long id) {
        return depositRepository.findById(id);
    }

    @Override
    public Deposit save(Deposit deposit) {
       return depositRepository.save(deposit);
    }

    @Override
    public void remove(Deposit deposit) {
        depositRepository.delete(deposit);
    }
}
