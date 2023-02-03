package com.iobuilders.bank.account.infrastructure.out;

import com.iobuilders.bank.account.domain.Money;
import com.iobuilders.bank.account.domain.Transaction;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class TransactionEntity {
    private double amount;
    private LocalDateTime created;
    private String fromAccount;
    private String id;

    public TransactionEntity(String id, double amount, LocalDateTime when, String fromAccount) {
        this.id = id;
        this.amount = amount;
        this.created = when;
        this.fromAccount = fromAccount;
    }

    public TransactionEntity() {
    }

    public static TransactionEntity fromTransaction(Transaction transaction) {
        return new TransactionEntity(transaction.getId(), transaction.getAmount().value(), transaction.getWhen(), transaction.getFromAccount());
    }

    public Transaction toTransaction() {
        return new Transaction(this.id, new Money(this.amount), this.created, this.fromAccount);
    }
}
