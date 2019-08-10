package com.demo.induction.tp;

import com.demo.induction.entity.Transaction;
import com.demo.induction.entity.Violation;
import com.demo.induction.parser.Parser;
import com.demo.induction.parser.XMLParser;
import com.demo.induction.validators.Validator;

import java.io.InputStream;
import java.util.List;

class XMLTransactionProcessor implements TransactionProcessor {
    private List<Transaction> mTransactions;

    @Override
    public void importTransactions(InputStream is) {
        Parser<Transaction> parser = new XMLParser(is);
        mTransactions = parser.parse();
    }

    @Override
    public List<Transaction> getImportedTransactions() {
        return mTransactions;
    }

    @Override
    public List<Violation> validate() {
        return Validator.validate(mTransactions);
    }

    @Override
    public boolean isBalanced() {
        return Validator.isBalanced(mTransactions);
    }
}
