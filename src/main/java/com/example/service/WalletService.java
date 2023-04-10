package com.example.service;

import java.util.List;

import com.example.model.Wallet;

public interface WalletService {
    List<Wallet> listAll();
     
    Wallet save(Wallet wallet);
     
    Wallet find(long id);

    Wallet withdraw(long id, int amount);

    Wallet add(long id, int amount);
     
    void delete(long id);
}
