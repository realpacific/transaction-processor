package com.demo.induction.tp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@Scope("prototype")
public class TransactionProcessorFactory {


    public TransactionProcessor get(String fileName) {
        TransactionProcessor tp;
        ClassLoader loader = getClass().getClassLoader();
        InputStream mInputStream = loader.getResourceAsStream(fileName);
        if (fileName.endsWith(".xml")) {
            tp = new XMLTransactionProcessor();
        } else if (fileName.endsWith(".csv")) {
            tp = new CSVTransactionProcessor();
        } else {
            throw new UnsupportedOperationException("Only xml or csv file");
        }
        tp.importTransactions(mInputStream);
        return tp;
    }
}
