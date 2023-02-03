package com.iobuilders.bank.user.domain;

public class InvalidValueException extends Exception {
    public InvalidValueException(String msg) {
        super(msg);
    }
}
