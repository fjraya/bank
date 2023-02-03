package com.iobuilders.bank.user.infrastructure.controller;


import com.iobuilders.bank.user.application.ports.input.RegisterUserException;
import com.iobuilders.bank.user.application.service.UserService;
import com.iobuilders.bank.user.domain.InvalidValueException;
import com.iobuilders.bank.user.domain.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseEntity<Token> createUser(@RequestBody CreateUserCommand createUserCommand) throws InvalidValueException, RegisterUserException {
        Token token = userService.createUser(createUserCommand).getToken();
        return ResponseEntity.ok(token);
    }
}
