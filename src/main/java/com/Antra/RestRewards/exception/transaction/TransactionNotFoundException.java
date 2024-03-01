package com.Antra.RestRewards.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends TransactionException {
    public TransactionNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
