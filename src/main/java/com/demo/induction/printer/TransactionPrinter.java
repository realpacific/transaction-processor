package com.demo.induction.printer;

import com.demo.induction.tp.TransactionProcessor;

import static com.demo.induction.utils.Utils.printList;

public class TransactionPrinter implements Printer<TransactionProcessor> {
    @Override
    public void print(TransactionProcessor tp) {
        System.out.println("Transactions:");
        printList(tp.getImportedTransactions());
        System.out.println("Is balanced? " + tp.isBalanced());
        System.out.println("Violations:");
        printList(tp.validate());
        System.out.println("\n");
    }
}
