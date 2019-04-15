package com.demo.induction.controllers;

import com.demo.induction.entity.Transaction;
import com.demo.induction.tp.TransactionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;

@RestController
public class TransactionController {


    @Autowired
    @Qualifier("csv")
    private TransactionProcessor csvProcessor;


    @Autowired
    @Qualifier("xml")
    private TransactionProcessor xmlProcessor;

    @GetMapping("/transactions")
    public List<Transaction> getTransaction(@RequestParam("fileName") String fileName) {
        TransactionProcessor tp;
        ClassLoader loader = getClass().getClassLoader();
        InputStream mInputStream = loader.getResourceAsStream(fileName);
        if (fileName.endsWith(".xml")) {
            tp = xmlProcessor;
        } else if (fileName.endsWith(".csv")) {
            tp = csvProcessor;
        } else {
            throw new UnsupportedOperationException("Only xml or csv file");
        }
        tp.importTransactions(mInputStream);
        return tp.getImportedTransactions();
    }

}
