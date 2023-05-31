package com.example.SpringBootDemo.dao;

import com.example.SpringBootDemo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    List<Transaction> findByNameContainingIgnoreCase(String name);

    List<Transaction> findByTransTypeEquals(String transType);

    List<Transaction> findByAmountEquals(Integer amount);

    List<Transaction> findByEntryDateEquals(String entryDate);
}
