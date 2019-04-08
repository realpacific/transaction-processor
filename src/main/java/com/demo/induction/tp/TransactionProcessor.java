package com.demo.induction.tp;

import com.demo.induction.entity.Transaction;
import com.demo.induction.entity.Violation;

import java.io.InputStream;
import java.util.List;


public interface TransactionProcessor {

    void importTransactions(InputStream is);

    List<Transaction> getImportedTransactions();

    List<Violation> validate();

    boolean isBalanced();
}
