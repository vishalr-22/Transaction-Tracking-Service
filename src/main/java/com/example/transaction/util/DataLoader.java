package com.example.transaction.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.transaction.model.Transaction;

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

            if (!line.equals("SchoolYr,Campus,StudentID,EntryDate,GradeLevel,Name"))
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
                    transaction.setTransactionType(items[2]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    Date entryDate = Date.valueOf(LocalDate.parse(items[3], formatter));

                    transaction.setEntryDate(entryDate);
                    transaction.setAmount(Integer.valueOf(items[4]));
                    transaction.setName(items[5]);
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
