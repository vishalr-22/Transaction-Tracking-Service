package com.example.transaction.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.transaction.errorHandler.TransactionNotFoundException;
import com.example.transaction.model.Transaction;
import com.example.transaction.repositories.TransactionRepository;
import com.example.transaction.util.DataLoader;

public class TransactionService implements IService{
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private DataLoader dataLoader;

    private Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public List<Transaction> findAll() {
        List<Transaction> result = repository.findAll();
        return result;
    }

    public Transaction addNewTransaction(Transaction newTransaction) {
        return repository.save(newTransaction);
    }

    public Transaction findOneTransactionId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public List<Transaction> findBySearchCriteria(Transaction searchCriteria) {
        // Goal is to return Union of various search result based on criteria
        List<Transaction> result = findAll();
        if (searchCriteria.getName() == null
                && searchCriteria.getTransactionType() == null
                && searchCriteria.getEntryDate() == null
                && searchCriteria.getAmount() == null)
            return result;

        HashMap<Long, Transaction> TransactionHashMap = new HashMap<>();
        System.out.println("search Criteria" + searchCriteria);
        if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
            repository
                    .findByNameContainingIgnoreCase(searchCriteria.getName())
                    .forEach(s -> TransactionHashMap.put(s.getTransactionId(), s));

        } else if (searchCriteria.getTransactionType() != null) {
            result.stream()
                    .filter(a -> a.getTransactionType().equals(searchCriteria.getTransactionType()))
                    .forEach(s -> TransactionHashMap.put(s.getTransactionId(), s));

        } else if (searchCriteria.getEntryDate() != null) {
            result.stream()
                    .filter(a -> a.getEntryDate().equals(searchCriteria.getEntryDate()))
                    .forEach(s -> TransactionHashMap.put(s.getTransactionId(), s));

        } else if (searchCriteria.getAmount() != null) {
            result.stream()
                    .filter(a -> a.getAmount().equals(searchCriteria.getAmount()))
                    .forEach(s -> TransactionHashMap.put(s.getTransactionId(), s));
        }
        return new ArrayList<Transaction>(TransactionHashMap.values());
    }

    private List<Transaction> priorityBasedSearch(Transaction searchCriteria) {
        List<Transaction> result = new ArrayList<>();
        if (searchCriteria.getName() != null || !searchCriteria.getName().isEmpty()) {
            result = repository.findByNameContainingIgnoreCase(searchCriteria.getName());
        } else if (searchCriteria.getTransactionType() != null) {
            result = repository.findByTransactionType(searchCriteria.getTransactionType());
        } else if (searchCriteria.getEntryDate() != null) {
            result = repository.findByEntryDate(searchCriteria.getEntryDate());
        } else if (searchCriteria.getAmount() != null) {
            result = repository.findByAmount(searchCriteria.getAmount());
        }
        return result;
    }

    public Transaction saveOrUpdate(Transaction newTransaction, Long id) {
        if (null != id) {
            Transaction Transaction = repository.getOne(id);
            if (Transaction != null) {
                Transaction.setAmount(newTransaction.getAmount());
                Transaction.setTransactionType(newTransaction.getTransactionType());
                Transaction.setEntryDate(newTransaction.getEntryDate());
                Transaction.setName(newTransaction.getName());
                return repository.save(Transaction);
            }
        }
        return repository.save(newTransaction);
    }

    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }

    public void loadInitialDataFromFile(String fileName) {
        try {
            List<Transaction> Transactions = dataLoader.loadTransactionRecords(fileName);
            Transactions.stream().peek(s -> System.out.println("saving" + s)).forEach(s -> repository.save(s));
        } catch (Exception ex) {
            logger.error("Could not load the Transaction record in system: " + ex.getMessage());
        }
    }

    private Date toSqlDate(String rawDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return Date.valueOf(LocalDate.parse(rawDate, formatter));
    }
}
