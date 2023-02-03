package com.iobuilders.bank.account.application.ports.input;

import com.iobuilders.bank.account.infrastructure.controller.TransferCommand;

public interface TransferUseCase {
    public void transfer(String userId, TransferCommand transferCommand) throws WalletException;
}
