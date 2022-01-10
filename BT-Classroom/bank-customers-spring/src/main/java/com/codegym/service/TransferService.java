package com.codegym.service;

import com.codegym.model.Transfer;
import com.codegym.repository.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferService implements ITransferService{

    @Autowired
    private ITransferRepository transferRepository;


    @Override
    public Iterable<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return transferRepository.findById(id);
    }

    @Override
    public Transfer save(Transfer transfer) {
       return transferRepository.save(transfer);
    }

    @Override
    public void remove(Transfer transfer) {
        transferRepository.delete(transfer);
    }
}
