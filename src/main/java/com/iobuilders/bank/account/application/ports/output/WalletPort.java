package com.iobuilders.bank.account.application.ports.output;

import com.iobuilders.bank.account.domain.Wallet;

import java.util.Optional;

public interface WalletPort {
    Wallet saveWallet(Wallet wallet);
    Optional<Wallet> getWalletByUserIdAndWalletId(String userId, String walletId);
}
