package com.example.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.transaction.service.TransactionService;

@SpringBootApplication
public class TransactionApplication {

	@Autowired
    TransactionService transactionService;
    final String fileName = "TransactionRecords.csv";

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}
	@Bean
    CommandLineRunner initDatabase() {
        return args -> {
            transactionService.loadInitialDataFromFile(fileName);
        };
    }

}
