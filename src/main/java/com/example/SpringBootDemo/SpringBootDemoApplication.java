package com.example.SpringBootDemo;

import com.example.SpringBootDemo.dao.TransactionRepository;
import com.example.SpringBootDemo.model.Transaction;
import com.example.SpringBootDemo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringBootDemoApplication {
    @Autowired
    TransactionService transactionService;
    final String fileName = "TransactionRecords.csv";

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            transactionService.loadInitialDataFromFile(fileName);
        };
    }
}
