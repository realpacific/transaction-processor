package com.demo.induction.tp;

import com.demo.induction.exceptions.TransactionNotFound;
import com.demo.induction.exceptions.UnsupportedExtensionException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.InputStream;

import static com.demo.induction.utils.Constants.EXTENSION_CSV;
import static com.demo.induction.utils.Constants.EXTENSION_XML;

@Component
@Scope("prototype")
public class TransactionProcessorFactory {


    public TransactionProcessor get(String fileName) {
        if (!fileName.endsWith(EXTENSION_CSV) && !fileName.endsWith(EXTENSION_XML)) {
            throw new UnsupportedExtensionException();
        }
        TransactionProcessor tp = null;
        ClassLoader loader = getClass().getClassLoader();
        InputStream mInputStream = loader.getResourceAsStream(fileName);
        if (mInputStream == null) throw new TransactionNotFound();

        if (fileName.endsWith(EXTENSION_XML)) {
            tp = new XMLTransactionProcessor();
        } else if (fileName.endsWith(EXTENSION_CSV)) {
            tp = new CSVTransactionProcessor();
        }
        if (tp == null) throw new UnsupportedExtensionException();
        tp.importTransactions(mInputStream);
        return tp;
    }
}
