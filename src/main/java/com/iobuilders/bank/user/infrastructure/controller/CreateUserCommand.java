package com.iobuilders.bank.user.infrastructure.controller;

public class CreateUserCommand {
    private String username;
    private String password;

    public CreateUserCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
