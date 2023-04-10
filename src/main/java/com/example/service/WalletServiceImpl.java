package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Wallet;
import com.example.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository repo;
     
    public List<Wallet> listAll() {
        return repo.findAll();
    }
     
    public Wallet save(Wallet wallet) {
        return repo.save(wallet);
    }
     
    public Wallet find(long id) {
        return repo.findById(id).get();
    }
     
    public Wallet withdraw(long id, int amount) {
        Wallet wallet = find(id);

		Integer newBalance = wallet.getAmountInCents() - amount;

		wallet.setAmountInCents(newBalance);

        return repo.save(wallet);
    }

    public Wallet add(long id, int amount) {
        Wallet wallet = find(id);

		Integer newBalance = wallet.getAmountInCents() + amount;

		wallet.setAmountInCents(newBalance);

        return repo.save(wallet);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
