package com.example.transaction.service;

import java.util.List;

import com.example.transaction.model.Transaction;

public interface IService {
    List<Transaction> findAll();
}
