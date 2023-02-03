package com.iobuilders.bank.account.application.ports.input;

import com.iobuilders.bank.account.domain.Wallet;
import com.iobuilders.bank.account.infrastructure.controller.CreateWalletCommand;

public interface CreateWalletUseCase {
    Wallet createWallet(String userId, CreateWalletCommand wallet);
}
