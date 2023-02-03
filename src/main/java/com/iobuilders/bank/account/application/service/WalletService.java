package com.iobuilders.bank.account.application.service;

import com.iobuilders.bank.account.application.ports.input.*;
import com.iobuilders.bank.account.application.ports.output.WalletPort;
import com.iobuilders.bank.account.domain.Money;
import com.iobuilders.bank.account.domain.Wallet;
import com.iobuilders.bank.account.infrastructure.controller.CreateWalletCommand;
import com.iobuilders.bank.account.infrastructure.controller.DepositWalletCommand;
import com.iobuilders.bank.account.infrastructure.controller.TransferCommand;

import java.util.Optional;

public class WalletService implements CreateWalletUseCase, DepositUseCase, TransferUseCase, GetWalletUseCase {
    private WalletPort walletPort;
    public WalletService(WalletPort walletPort) {
        this.walletPort = walletPort;
    }
    @Override
    public Wallet createWallet(String userId, CreateWalletCommand wallet) {
        return walletPort.saveWallet(new Wallet(userId, wallet.getInitBalance()));
    }


    @Override
    public void deposit(String userId, String id, DepositWalletCommand depositWalletCommand) throws WalletException {
        Wallet retrievedWallet = getWallet(userId, id);
        retrievedWallet.deposit(new Money(depositWalletCommand.getAmount()));
        walletPort.saveWallet(retrievedWallet);
    }

    @Override
    public void transfer(String userId, TransferCommand transferCommand) throws WalletException {
        Wallet fromWallet = getWallet(userId, transferCommand.getFromAccount());
        Wallet toWallet = getWallet(userId, transferCommand.getToAccount());
        Money amount = new Money(transferCommand.getAmount());
        fromWallet.withdraw(amount);
        toWallet.deposit(amount, fromWallet.getId());
        //TO-DO: Aquí hay un tema de transaccionalidad, habría que mirar una implementación
        // de UnitOfWork desacoplada del framework, o definir
        walletPort.saveWallet(fromWallet);
        walletPort.saveWallet(toWallet);
    }

    public Wallet getWallet(String userId, String id) throws WalletException {
        Optional<Wallet> wallet = walletPort.getWalletByUserIdAndWalletId(userId, id);
        if (wallet.isEmpty()) throw new WalletException("Wallet with id " + id + " doesn't exist");
        return wallet.get();
    }

}
