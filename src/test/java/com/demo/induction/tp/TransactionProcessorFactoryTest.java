package com.demo.induction.tp;

import com.demo.induction.entity.Transaction;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionProcessorFactoryTest {

    @Autowired
    private TransactionProcessorFactory factory;

    private TransactionProcessor dataCsv;
    private TransactionProcessor dataXml;
    private TransactionProcessor badCsv;


    @Before
    public void setup() {
        dataXml = factory.get("data.xml");
        badCsv = factory.get("bad.csv");
        dataCsv = factory.get("data.csv");
    }


    @Test
    public void testForNumberOfTransactions() {
        assertEquals(5, dataXml.getImportedTransactions().size());
        assertEquals(5, badCsv.getImportedTransactions().size());
        assertEquals(5, dataCsv.getImportedTransactions().size());
    }

    @Test
    public void testForDifferenceInCSVParsers() {
        assertNotEquals("The dataCSV and badCsv should be different", dataCsv, badCsv);
        Assertions.assertThat(dataCsv).isNotEqualTo(badCsv);
    }

    @Test
    public void testForNumberOfViolations() {
        assertEquals(2, badCsv.validate().size());
        assertEquals(0, dataCsv.validate().size());
        assertEquals(0, dataXml.validate().size());
    }

    @Test
    public void badCsvMustContainEAndOtherShouldNot() {
        Assertions.assertThat(badCsv.getImportedTransactions()).flatExtracting(Transaction::getType).contains("E");
    }

    @Test
    public void testForValidTransactionTypeInGoodFiles() {
        Assertions.assertThat(dataXml.getImportedTransactions()).flatExtracting(Transaction::getType)
                .containsAnyElementsOf(Arrays.asList("C", "D"));
        Assertions.assertThat(dataCsv.getImportedTransactions()).flatExtracting(Transaction::getType)
                .containsOnlyElementsOf(Arrays.asList("C", "D"));
    }

    @Test
    public void testForBalancenessOfTransactions() {
        assertTrue(badCsv.isBalanced());
        assertFalse(dataCsv.isBalanced());
        assertFalse(dataXml.isBalanced());
    }
}