package com.demo.induction.controllers;

import com.demo.induction.entity.BaseResponse;
import com.demo.induction.entity.Transaction;
import com.demo.induction.printer.Printer;
import com.demo.induction.tp.TransactionProcessor;
import com.demo.induction.tp.TransactionProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.demo.induction.utils.Constants.MESSAGE_OK;

@RestController
public class TransactionController {

    @Autowired
    TransactionProcessorFactory factory;

    @Autowired
    Printer<TransactionProcessor> printer;

    /**
     * Endpoint for getting input file from user
     *
     * @param fileName the file name of csv or xml file (data.csv, bad.csv, data.xml)
     * @return the {@link BaseResponse} containing list of {@link Transaction}
     */
    @GetMapping("/transactions")
    public BaseResponse<List<Transaction>> getTransaction(@RequestParam("fileName") String fileName) {
        TransactionProcessor tp = factory.get(fileName);
        printer.print(tp);
        return new BaseResponse<>(tp.getImportedTransactions(), MESSAGE_OK);
    }

    @GetMapping("/transactions/csv")
    public BaseResponse<List<Transaction>> getCSVTransaction() {
        TransactionProcessor tp = factory.get("data.csv");
        printer.print(tp);
        return new BaseResponse<>(tp.getImportedTransactions(), MESSAGE_OK);
    }


    @GetMapping("/transactions/xml")
    public BaseResponse<List<Transaction>> getXMLTransaction() {
        TransactionProcessor tp = factory.get("data.xml");
        printer.print(tp);
        return new BaseResponse<>(tp.getImportedTransactions(), MESSAGE_OK);
    }

}
