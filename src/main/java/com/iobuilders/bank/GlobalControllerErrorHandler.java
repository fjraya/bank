package com.iobuilders.bank;

import com.iobuilders.bank.account.application.ports.input.WalletException;
import com.iobuilders.bank.user.application.ports.input.InvalidTokenException;
import com.iobuilders.bank.user.application.ports.input.RegisterUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

class ErrorInfo {
    private String code;
    private String message;

    public ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}


@ControllerAdvice
public class GlobalControllerErrorHandler {
    @ExceptionHandler(WalletException.class)
    public ResponseEntity<ErrorInfo> handleInvalidWalletException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInfo("-4", extractMessage(ex)));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorInfo> handleInvalidTokenException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorInfo("-5", extractMessage(ex)));
    }


    @ExceptionHandler(RegisterUserException.class)
    public ResponseEntity<ErrorInfo> handleRegisterUserException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorInfo("-5", extractMessage(ex)));
    }


    private String extractMessage(Exception e) {

        return e.getMessage();

    }
}
