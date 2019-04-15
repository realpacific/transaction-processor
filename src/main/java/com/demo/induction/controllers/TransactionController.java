package com.demo.induction.controllers;

import com.demo.induction.entity.Transaction;
import com.demo.induction.printer.Printer;
import com.demo.induction.tp.TransactionProcessor;
import com.demo.induction.tp.TransactionProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionProcessorFactory factory;

    @Autowired
    Printer<TransactionProcessor> printer;

    @GetMapping("/transactions")
    public List<Transaction> getTransaction(@RequestParam("fileName") String fileName) {
        TransactionProcessor tp = factory.get(fileName);
        printer.print(tp);
        return tp.getImportedTransactions();
    }

}
