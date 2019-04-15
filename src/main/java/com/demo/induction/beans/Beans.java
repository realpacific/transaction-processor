package com.demo.induction.beans;

import com.demo.induction.tp.CSVTransactionProcessor;
import com.demo.induction.tp.TransactionProcessor;
import com.demo.induction.tp.XMLTransactionProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Qualifier("xml")
    @Bean
    TransactionProcessor providesXMLTransactionProcessor() {
        return new XMLTransactionProcessor();
    }


    @Qualifier("csv")
    @Bean
    TransactionProcessor providesCSVTransactionProcessor() {
        return new CSVTransactionProcessor();
    }



}
