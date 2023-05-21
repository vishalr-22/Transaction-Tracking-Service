package com.example.transaction.repositories;

import java.sql.Date;
import java.util.List;
import com.example.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    List<Transaction> findByNameContainingIgnoreCase(String name);

    List<Transaction> findByTransTypeEquals(String transType);

    List<Transaction> findByAmountEquals(Integer amount);

    List<Transaction> findByEntryDateEquals(Date entryDate);
}
