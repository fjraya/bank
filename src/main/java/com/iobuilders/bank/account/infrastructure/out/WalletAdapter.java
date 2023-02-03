package com.iobuilders.bank.account.infrastructure.out;

import com.iobuilders.bank.account.application.ports.output.WalletPort;
import com.iobuilders.bank.account.domain.Wallet;

import java.util.Optional;

public class WalletAdapter implements WalletPort {
    private WalletRepository walletRepository;
    public WalletAdapter(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(WalletEntity.fromWallet(wallet)).toWallet();
    }

    @Override
    public Optional<Wallet> getWalletByUserIdAndWalletId(String userId, String walletId) {
        Optional<WalletEntity> walletEntity = walletRepository.findByUserIdAndId(userId, walletId);
        return walletEntity.map(WalletEntity::toWallet);
    }
}
