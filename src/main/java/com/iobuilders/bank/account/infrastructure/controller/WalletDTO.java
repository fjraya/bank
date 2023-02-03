package com.iobuilders.bank.account.infrastructure.controller;

import com.iobuilders.bank.account.domain.Wallet;

import java.util.List;
import java.util.stream.Collectors;

public class WalletDTO {
    private String id;
    private Double balance;
    private List<TransactionDTO> transactions;

    public WalletDTO(String id, Double balance, List<TransactionDTO> transactions) {
        this.id = id;
        this.balance = balance;
        this.transactions = transactions;
    }

    public static WalletDTO fromDomain(Wallet wallet) {
        Double balance = wallet.getBalance().value();
        List<TransactionDTO> transactions = wallet.getTransactions().stream().map(TransactionDTO::fromDomain).collect(Collectors.toList());
        return new WalletDTO(wallet.getId(), balance, transactions);
    }

    public String getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }
}
