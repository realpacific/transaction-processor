package com.demo.induction.exceptions;

public class TransactionNotFound extends RuntimeException {
    public TransactionNotFound() {
        super("The requested file could not be found.");
    }
}
