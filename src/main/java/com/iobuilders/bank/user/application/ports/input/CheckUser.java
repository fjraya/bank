package com.iobuilders.bank.user.application.ports.input;

public interface CheckUser {
    String checkUser(String authorization) throws InvalidTokenException;
}
