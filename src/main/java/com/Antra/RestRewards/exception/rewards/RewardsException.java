package com.Antra.RestRewards.exception.rewards;

public class RewardsException extends RuntimeException {

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public RewardsException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public RewardsException() {
        super();
    }
}
