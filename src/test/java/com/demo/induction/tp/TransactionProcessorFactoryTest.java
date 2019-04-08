package com.demo.induction.tp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionProcessorFactoryTest {


    private TransactionProcessorFactory dataCsv;
    private TransactionProcessorFactory dataXml;
    private TransactionProcessorFactory badCsv;

    @Before
    public void setup() {
        dataCsv = new TransactionProcessorFactory("data.csv");
        dataXml = new TransactionProcessorFactory("data.xml");
        badCsv = new TransactionProcessorFactory("bad.csv");
    }


    @Test
    public void testForNumberOfTransactions() {
        assertEquals(5, badCsv.get().getImportedTransactions().size());
        assertEquals(5, dataXml.get().getImportedTransactions().size());
        assertEquals(5, dataCsv.get().getImportedTransactions().size());
    }

    @Test
    public void testForNumberOfViolations() {
        assertEquals(2, badCsv.get().validate().size());
        assertEquals(0, dataCsv.get().validate().size());
        assertEquals(0, dataXml.get().validate().size());
    }

    @Test
    public void testForBalancenessOfTransactions() {
        assertTrue(badCsv.get().isBalanced());
        assertFalse(dataCsv.get().isBalanced());
        assertFalse(dataXml.get().isBalanced());
    }
}