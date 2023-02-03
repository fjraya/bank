package com.iobuilders.bank.user.domain;

import com.iobuilders.bank.user.shared.GenerateKey;

public class Token implements ValueObject<String> {
    private String token;
    static final int LENGTH = 15;

    public Token(String token) throws InvalidValueException {
        if (token == null) throw new InvalidValueException("Token cannot be null");
        if (token.isBlank()) throw new InvalidValueException("Token cannot be blank");
        this.token = token;
    }

    public static Token newToken() throws InvalidValueException {
        return new Token(GenerateKey.generateRandomIdWithSeed(LENGTH));
    }

    public String getToken() {
        return token;
    }

    @Override
    public String value() {
        return token;
    }
}
