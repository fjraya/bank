package com.iobuilders.bank.account.domain;

import com.iobuilders.bank.account.application.ports.input.WalletException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Wallet {
    private String id;
    private Set<Transaction> transactions;
    private Money initBalance;
    private String userId;

    public Wallet(String userId, double initBalance) {
        this(UUID.randomUUID().toString(), new HashSet<>(), initBalance, userId);
    }

    public Wallet(String id, Set<Transaction> transactions, double initBalance, String userId) {
        this.id = id;
        this.transactions = transactions;
        this.initBalance = new Money(initBalance);
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public Money getBalance() {
        Money result = new Money();
        for (Transaction transaction: transactions) {
            result = result.add(transaction.getAmount());
        }
        return result.add(this.initBalance);
    }

    public boolean deposit(Money money) throws WalletException {
        return deposit(money, null);
    }

    public boolean deposit(Money money, String fromSource) throws WalletException {
        if (money.greaterOrEqualsThan(new Money(0.0))) {
            Transaction deposit = new Transaction(money, fromSource);
            this.transactions.add(deposit);
            return true;
        }
        else throw new WalletException("Cannnot make negative deposits");

    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public String getUserId() {
        return userId;
    }

    public boolean withdraw(Money amount) throws WalletException {
        if (!hasEnoughFunds(amount)) {
            throw new WalletException("There aren't enought funds to make the transfer");
        }
        Transaction transaction = new Transaction(amount.minus());
        this.transactions.add(transaction);
        return true;
    }

    private boolean hasEnoughFunds(Money money) {
        return this.getBalance().greaterOrEqualsThan(money);
    }

}
