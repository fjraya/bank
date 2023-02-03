package com.iobuilders.bank.account.application.ports.input;

import com.iobuilders.bank.account.domain.Wallet;

public interface GetWalletUseCase {
    public Wallet getWallet(String userId, String id) throws WalletException;
}
