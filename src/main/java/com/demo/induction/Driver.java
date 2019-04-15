package com.demo.induction;

import com.demo.induction.printer.Printer;
import com.demo.induction.printer.TransactionPrinter;
import com.demo.induction.tp.TransactionProcessor;
import com.demo.induction.tp.TransactionProcessorFactory;

import static com.demo.induction.utils.Utils.printDecorator;

public class Driver {
   /* public static void main(String[] args) {
        String[] files = new String[]{"data.csv", "data.xml", "bad.csv"};
        Printer printer = new TransactionPrinter();

        for (String file : files) {
            printDecorator(file);
            try {
                TransactionProcessorFactory dataCSV = new TransactionProcessorFactory();
                TransactionProcessor tp = dataCSV.get(file);
                printer.print(tp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

}
