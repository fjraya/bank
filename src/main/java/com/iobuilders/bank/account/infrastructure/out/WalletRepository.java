package com.iobuilders.bank.account.infrastructure.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletEntity, String> {
    public Optional<WalletEntity> findByUserIdAndId(String userId, String id);
}
