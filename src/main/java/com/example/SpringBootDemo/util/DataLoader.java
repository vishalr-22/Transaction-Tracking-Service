package com.example.SpringBootDemo.util;

import com.example.SpringBootDemo.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoader {
    public List<Transaction> loadTransactionRecords(String fileName) throws IOException {
        List<Transaction> result = new ArrayList<>();
        File file = ResourceUtils.getFile("classpath:" + fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            // Read first line
            String line = br.readLine();
            // Make sure file has correct headers
            if (line == null) throw new IllegalArgumentException("File is empty");

            if (!line.equals("transactionId,name,transType,entryDate,amount"))
                throw new IllegalArgumentException("File has wrong columns: " + line);

            // Run through following lines
            while ((line = br.readLine()) != null) {
                // Break line into entries using comma
                String[] items = line.split(",");
                try {
                    if (items.length > 6) throw new ArrayIndexOutOfBoundsException();
                    Transaction transaction = new Transaction();

                    transaction.setTransactionId(Long.valueOf(items[0]));
                    transaction.setName(items[1]);
                    transaction.setTransType(items[2]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    Date entryDate = Date.valueOf(LocalDate.parse(items[3], formatter));
                    System.out.print(entryDate);
                    transaction.setEntryDate(entryDate.toString());
                    transaction.setAmount(Integer.valueOf(items[4]));
                    result.add(transaction);
                } catch (Exception e) {
                    System.out.println("Invalid Record Found: " + line);
                }
            }
            return result;
        } finally {
            br.close();
        }
    }

}
