package com.iobuilders.bank.user.domain;

public interface PasswordEncoder {
    public String encode(String password);
}
