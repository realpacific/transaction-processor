package com.demo.induction.tp;

import com.demo.induction.exceptions.TransactionNotFound;
import com.demo.induction.exceptions.UnsupportedExtensionException;

import java.io.InputStream;

import static com.demo.induction.utils.Constants.EXTENSION_CSV;
import static com.demo.induction.utils.Constants.EXTENSION_XML;

public class TransactionProcessorFactory {

    public static TransactionProcessor get(String fileName) {
        if (!fileName.endsWith(EXTENSION_CSV) && !fileName.endsWith(EXTENSION_XML)) {
            throw new UnsupportedExtensionException();
        }
        TransactionProcessor tp = null;
        ClassLoader loader = TransactionProcessorFactory.class.getClassLoader();
        InputStream mInputStream = loader.getResourceAsStream(fileName);
        if (mInputStream == null) throw new TransactionNotFound();

        if (fileName.endsWith(EXTENSION_XML)) tp = new XMLTransactionProcessor();
        else if (fileName.endsWith(EXTENSION_CSV)) tp = new CSVTransactionProcessor();
        if (tp == null) throw new UnsupportedExtensionException();
        tp.importTransactions(mInputStream);
        return tp;
    }
}
