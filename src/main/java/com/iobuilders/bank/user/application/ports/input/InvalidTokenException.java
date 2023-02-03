package com.iobuilders.bank.user.application.ports.input;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(String msg) {
        super(msg);
    }
}
