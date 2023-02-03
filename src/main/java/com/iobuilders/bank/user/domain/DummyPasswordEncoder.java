package com.iobuilders.bank.user.domain;

public class DummyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(String password) {
        return password;
    }
}
