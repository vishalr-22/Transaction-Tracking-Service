package com.example.transaction.controller;

import com.example.transaction.model.Transaction;
import com.example.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping(value = "/Transaction/edit/{id}", method = RequestMethod.POST)
    ModelAndView updateTransactionRecord(@ModelAttribute("editTransaction") Transaction newTransaction, @PathVariable("id") Long id) {
        transactionService.saveOrUpdate(newTransaction, id);
        return home();
    }

    @RequestMapping("/Transaction/delete")
    ModelAndView deleteTransaction(@RequestParam String TransactionId) {
        transactionService.deleteTransaction(Long.valueOf(TransactionId));
        return home();
    }

    @RequestMapping(value = "/Transaction/edit")
    ModelAndView editTransaction(@RequestParam String TransactionId) {
        ModelAndView model = new ModelAndView("edit");
        Transaction Transaction = transactionService.findOneTransactionId(Long.valueOf(TransactionId));
        model.addObject("Transaction", Transaction);
        return model;
    }

    @RequestMapping(value = "/Transaction/add", method = RequestMethod.GET)
    ModelAndView addTransaction() {
        ModelAndView model = new ModelAndView("addTransaction");
        return model;
    }
}
