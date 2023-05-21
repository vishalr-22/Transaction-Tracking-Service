package com.example.transaction.errorHandler;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(Long id) {
        super("Transaction id not found : " + id);
    }
}
