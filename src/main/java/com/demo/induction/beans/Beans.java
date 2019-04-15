package com.demo.induction.beans;

import com.demo.induction.printer.Printer;
import com.demo.induction.printer.TransactionPrinter;
import com.demo.induction.tp.CSVTransactionProcessor;
import com.demo.induction.tp.TransactionProcessor;
import com.demo.induction.tp.XMLTransactionProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Beans {

    @Qualifier("xml")
    @Bean
    @Scope(value = "prototype")
    TransactionProcessor providesXMLTransactionProcessor() {
        return new XMLTransactionProcessor();
    }


    @Qualifier("csv")
    @Bean
    @Scope(value = "prototype")
    TransactionProcessor providesCSVTransactionProcessor() {
        return new CSVTransactionProcessor();
    }

    @Bean
    Printer<TransactionProcessor> providesTransactionPrinter() {
        return new TransactionPrinter();
    }


}
