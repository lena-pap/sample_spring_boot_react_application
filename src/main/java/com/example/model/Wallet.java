package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
 
@Entity
@Table(name = "wallets")
public class Wallet {
    public static final Integer MAX_AMOUNT_IN_CENTS = 10000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
     
    @Column
    private String name;

    @Min(value = 0, message = "Cannot withdraw that much money")
    @Max(value = 10000, message = "Cannot add that much money")
    @Column(name="amount_in_cents")
    private Integer amountInCents;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    public Wallet() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountInCents() {
        return amountInCents;
    }

    public void setAmountInCents(Integer amountInCents) {
        this.amountInCents = amountInCents;
    }
}