package com.demo.induction.beans;

import com.demo.induction.printer.Printer;
import com.demo.induction.printer.TransactionPrinter;
import com.demo.induction.tp.TransactionProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    Printer<TransactionProcessor> providesTransactionPrinter() {
        return new TransactionPrinter();
    }


}
