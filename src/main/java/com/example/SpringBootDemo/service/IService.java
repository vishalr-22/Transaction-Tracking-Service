package com.example.SpringBootDemo.service;

import com.example.SpringBootDemo.model.Transaction;

import java.util.List;

public interface IService {
    List<Transaction> findAll();
}
