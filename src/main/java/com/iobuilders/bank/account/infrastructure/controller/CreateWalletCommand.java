package com.iobuilders.bank.account.infrastructure.controller;

public class CreateWalletCommand {
    private double initBalance;

    public CreateWalletCommand() {
        this(0.0);
    }

    public CreateWalletCommand(double initBalance) {
        this.initBalance = initBalance;
    }

    public double getInitBalance() {
        return initBalance;
    }
}
