package com.example.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.model.Wallet;
import com.example.service.WalletService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/wallets")
public class WalletsController {
    @Autowired
	private WalletService walletService;

    @GetMapping
    public List<Wallet> getWallets() {
        return walletService.listAll();
    }

	@GetMapping("/{id}")
	public Wallet getWallet(@PathVariable Long id) {
        return walletService.find(id);
    }

	@PostMapping
    public ResponseEntity<Wallet> createWallet(@Valid @RequestBody Wallet wallet) throws URISyntaxException {
        Wallet savedWallet = walletService.save(wallet);

        return ResponseEntity.created(new URI("/wallets/" + savedWallet.id)).body(savedWallet);
    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<Wallet> withdraw(@PathVariable Long id, @RequestBody Integer amountInCents) throws URISyntaxException {
        Wallet savedWallet = walletService.withdraw(id, amountInCents);

        return ResponseEntity.created(new URI("/wallets/" + id)).body(savedWallet);
    }
    
    @PatchMapping("/{id}/add")
    public ResponseEntity<Wallet> add(@PathVariable Long id, @RequestBody Integer amountInCents) throws URISyntaxException {
        Wallet savedWallet = walletService.add(id, amountInCents);

        return ResponseEntity.created(new URI("/wallets/" + id)).body(savedWallet);
    }
}