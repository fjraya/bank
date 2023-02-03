package com.iobuilders.bank.account.infrastructure.out;

import com.iobuilders.bank.account.domain.Wallet;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "wallets")
public class WalletEntity {
    @Id
    private String id;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "wallet_transactions", joinColumns = @JoinColumn(name = "wallet_id"))
    private Set<TransactionEntity> transactions;
    private double initBalance;
    private String userId;

    public WalletEntity() {

    }

    public WalletEntity(Set<TransactionEntity> transactions, double initBalance, String userId) {
        this(UUID.randomUUID().toString(), transactions, initBalance, userId);
    }

    public WalletEntity(String id, Set<TransactionEntity> transactions, double initBalance, String userId) {
        this.id = id;
        this.transactions = transactions;
        this.initBalance = initBalance;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public static WalletEntity fromWallet(Wallet wallet) {
        return new WalletEntity(wallet.getId(), wallet.getTransactions().stream().map(TransactionEntity::fromTransaction).collect(Collectors.toSet()), 0, wallet.getUserId());
    }

    public Wallet toWallet() {
        return new Wallet(this.id, this.transactions.stream().map(TransactionEntity::toTransaction).collect(Collectors.toSet()), this.initBalance, this.userId);
    }
}
