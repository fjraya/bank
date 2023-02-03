package com.iobuilders.bank.account.infrastructure.controller;

public class DepositWalletCommand {
    private double amount;

    public DepositWalletCommand() {
        this(0.0);
    }

    public DepositWalletCommand(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
