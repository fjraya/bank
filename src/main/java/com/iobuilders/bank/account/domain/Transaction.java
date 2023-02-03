package com.iobuilders.bank.account.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private String id;
    private Money amount;
    private LocalDateTime when;
    private String fromAccount;

    public Transaction(Money amount, String fromAccount) {
        this(UUID.randomUUID().toString(), amount, LocalDateTime.now(), fromAccount);
    }

    public Transaction(Money amount) {
        this(amount, null);
    }

    public Transaction(String id, Money amount, LocalDateTime when, String fromAccount) {
        this.id = id;
        this.amount = amount;
        this.when = when;
        this.fromAccount = fromAccount;
    }

    public Money getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public String getFromAccount() {
        return fromAccount;
    }
}
