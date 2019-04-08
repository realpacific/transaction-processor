package com.demo.induction.tp;

import java.io.InputStream;

public class TransactionProcessorFactory {
    private String fileName;

    public TransactionProcessorFactory(String fileName) {
        this.fileName = fileName;
    }

    public TransactionProcessor get() {
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
