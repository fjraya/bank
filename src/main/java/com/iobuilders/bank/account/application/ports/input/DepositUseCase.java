package com.iobuilders.bank.account.application.ports.input;

import com.iobuilders.bank.account.infrastructure.controller.DepositWalletCommand;

public interface DepositUseCase {
    public void deposit(String userId, String id, DepositWalletCommand depositWalletCommand) throws WalletException;
}
