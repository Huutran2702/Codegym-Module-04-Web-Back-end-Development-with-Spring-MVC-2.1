package com.codegym.service;

import com.codegym.model.Withdraw;
import com.codegym.repository.IWithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawService implements IWithdrawService{

    @Autowired
    private IWithdrawRepository withdrawRepository;

    @Override
    public Iterable<Withdraw> findAll() {
        return withdrawRepository.findAll();
    }

    @Override
    public Optional<Withdraw> findById(Long id) {
        return withdrawRepository.findById(id);
    }

    @Override
    public Withdraw save(Withdraw withdraw) {
       return withdrawRepository.save(withdraw);
    }

    @Override
    public void remove(Withdraw withdraw) {
        withdrawRepository.delete(withdraw);
    }
}
