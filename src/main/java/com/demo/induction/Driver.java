package com.demo.induction;

import com.demo.induction.tp.TransactionProcessor;
import com.demo.induction.tp.TransactionProcessorFactory;

import static com.demo.induction.utils.Utils.printDecorator;
import static com.demo.induction.utils.Utils.printList;

public class Driver {
    public static void main(String[] args) {
        String[] files = new String[]{"data.csv", "data.xml", "bad.csv", "xyz"};
        for (String file : files) {
            printDecorator(file);
            try {
                TransactionProcessorFactory dataCSV = new TransactionProcessorFactory(file);
                TransactionProcessor tp = dataCSV.get();
                System.out.println("Transactions:");
                printList(tp.getImportedTransactions());
                System.out.println("Is balanced? " + tp.isBalanced());
                System.out.println("Violations:");
                printList(tp.validate());
                System.out.println("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
