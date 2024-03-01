package com.Antra.RestRewards.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTransactionException extends TransactionException {
    public InvalidTransactionException(String errorMessage) {
        super(errorMessage);
    }
}
