package com.example.SpringBootDemo.controller;

import com.example.SpringBootDemo.model.Transaction;
import com.example.SpringBootDemo.service.TransactionService;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("index");
        List<Transaction> allTransactions = transactionService.findAll();
        model.addObject("list", allTransactions);
        return model;
    }

    @PostMapping("/transaction/search")
    ModelAndView findAll(Transaction searchCriteria) {
    //     Transaction searchCriteriaObject = new Transaction(searchCriteria.get("transType").toString(), searchCriteria.get("name").toString(),
        // Date.valueOf(searchCriteria.get("entryDate").toString()), (Integer)searchCriteria.get("amount"));
        ModelAndView model = new ModelAndView("index");
        List<Transaction> result = transactionService.findBySearchCriteria(searchCriteria);
        model.addObject("list", result);
        return model;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/transaction/add", method = RequestMethod.POST)
    ModelAndView addNewTransaction(@ModelAttribute("addTransaction") Transaction newTransaction) {
        transactionService.saveOrUpdate(newTransaction, null);
        return home();
    }

    @RequestMapping(value = "/transaction/edit/{transactionId}", method = RequestMethod.POST)
    ModelAndView updateTransactionRecord(@ModelAttribute("editTransaction") Transaction newTransaction, @PathVariable("transactionId") Long transactionId) {
        System.out.println(newTransaction);
        transactionService.saveOrUpdate(newTransaction, transactionId);
        return home();
    }

    @RequestMapping("/transaction/delete")
    ModelAndView deleteTransaction(@RequestParam String transactionId) {
        transactionService.deleteTransaction(Long.valueOf(transactionId));
        return home();
    }

    @RequestMapping(value = "/transaction/edit")
    ModelAndView editTransaction(@RequestParam String transactionId) {
        System.out.println(transactionId);
        Transaction transaction = transactionService.findOneTransactionId(Long.valueOf(transactionId));
        
        ModelAndView model = new ModelAndView("edit", "editTransaction",transaction );
        
        // model.addObject("Transaction", transaction);
        System.out.println(transaction);
        return model;
    }

    @RequestMapping(value = "/transaction/add", method = RequestMethod.GET)
    ModelAndView addTransaction() {
        ModelAndView model = new ModelAndView("addTransaction");
        return model;
    }
}
