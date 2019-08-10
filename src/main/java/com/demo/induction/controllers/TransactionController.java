package com.demo.induction.controllers;

import com.demo.induction.entity.BaseResponse;
import com.demo.induction.aspect.ShouldBeLogged;
import com.demo.induction.entity.Transaction;
import com.demo.induction.printer.Printer;
import com.demo.induction.tp.TransactionProcessor;
import com.demo.induction.tp.TransactionProcessorFactory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.demo.induction.utils.Constants.MESSAGE_OK;

@RestController
@AllArgsConstructor
public class TransactionController {

    private final Printer<TransactionProcessor> printer;

    @GetMapping("/transactions")
    @ShouldBeLogged
    public BaseResponse<List<Transaction>> getTransaction(@RequestParam("fileName") String fileName) {
        TransactionProcessor tp = TransactionProcessorFactory.get(fileName);
        printer.print(tp);
        return new BaseResponse<>(tp.getImportedTransactions(), MESSAGE_OK);
    }

    @GetMapping("/transactions/csv")
    @ShouldBeLogged
    public BaseResponse<List<Transaction>> getCSVTransaction() {
        TransactionProcessor tp = TransactionProcessorFactory.get("data.csv");
        printer.print(tp);
        return new BaseResponse<>(tp.getImportedTransactions(), MESSAGE_OK);
    }


    @GetMapping("/transactions/xml")
    @ShouldBeLogged
    public BaseResponse<List<Transaction>> getXMLTransaction() {
        TransactionProcessor tp = TransactionProcessorFactory.get("data.xml");
        printer.print(tp);
        return new BaseResponse<>(tp.getImportedTransactions(), MESSAGE_OK);
    }

}
