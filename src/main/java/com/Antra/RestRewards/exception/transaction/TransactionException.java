package com.Antra.RestRewards.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class TransactionException extends RuntimeException{

    private String errorMessage;
    public TransactionException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public TransactionException() {
        super();
    }
}
