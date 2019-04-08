package com.demo.induction.parser;

import com.demo.induction.entity.Transaction;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CSVParser implements Parser<Transaction> {
    private InputStream mInputStream;

    public CSVParser(InputStream mInputStream) {
        this.mInputStream = mInputStream;
    }

    /**
     * Parses CSV files received from {@link CSVParser#mInputStream} using OpenCSV
     *
     * @return the list of {@link Transaction} in the file
     */
    @Override
    public List<Transaction> parse() {
        List<Transaction> transactions = new ArrayList<>();
        CSVReader csvReader;
        try {
            Reader reader = new InputStreamReader(mInputStream);
            csvReader = new CSVReader(reader);
            for (String[] record : csvReader.readAll()) {
                try {
                    transactions.add(new Transaction(record[0],
                            new BigDecimal(record[1]), record[2]));
                } catch (NumberFormatException e) {
                    transactions.add(new Transaction(record[0],
                            null, record[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return transactions;
    }
}
