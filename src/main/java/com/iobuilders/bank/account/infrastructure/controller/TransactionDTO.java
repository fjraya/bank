package com.iobuilders.bank.account.infrastructure.controller;

import com.iobuilders.bank.account.domain.Transaction;

public class TransactionDTO {
    private Double amount;
    private String when;
    private String fromAccount;

    private TransactionDTO(Double amount, String when, String fromAccount) {
        this.amount = amount;
        this.when = when;
        this.fromAccount = fromAccount;
    }

    public static TransactionDTO fromDomain(Transaction transaction) {
        return new TransactionDTO(transaction.getAmount().value(), transaction.getWhen().toString(), transaction.getFromAccount());
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }
}
