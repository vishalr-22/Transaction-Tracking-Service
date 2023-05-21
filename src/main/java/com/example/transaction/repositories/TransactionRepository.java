package com.example.transaction.repositories;

import java.util.Date;
import java.util.List;
import com.example.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    List<Transaction> findByNameContainingIgnoreCase(String name);

    List<Transaction> findByTransactionType(String transType);

    List<Transaction> findByAmount(Integer amount);

    List<Transaction> findByEntryDate(Date entryDate);
}
