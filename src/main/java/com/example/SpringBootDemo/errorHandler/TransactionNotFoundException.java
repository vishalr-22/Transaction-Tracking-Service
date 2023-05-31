package com.example.SpringBootDemo.errorHandler;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(Long id) {
        super("Transaction id not found : " + id);
    }
}
