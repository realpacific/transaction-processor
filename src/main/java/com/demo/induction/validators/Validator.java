package com.demo.induction.validators;

import com.demo.induction.entity.DataType;
import com.demo.induction.entity.Transaction;
import com.demo.induction.entity.Violation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    private Validator() {
    }

    public static List<Violation> validate(List<Transaction> list) {
        List<Violation> violations = new ArrayList<>();
        for (Transaction t : list) {
            if (!t.getType().equals("D") && !t.getType().equals("C"))
                violations.add(new Violation(list.indexOf(t) + 1,
                        DataType.TYPE.getXMLNodeName(), DataType.TYPE.getDescription()));
            if (t.getAmount() == null)
                violations.add(new Violation(list.indexOf(t) + 1,
                        DataType.AMOUNT.getXMLNodeName(), DataType.AMOUNT.getDescription()));
            if (t.getNarration() == null)
                violations.add(new Violation(list.indexOf(t) + 1,
                        DataType.NARRATION.getXMLNodeName(), DataType.NARRATION.getDescription()));

        }
        return violations;
    }

    public static boolean isBalanced(List<Transaction> list) {
        BigDecimal sumDebit = new BigDecimal(0);
        BigDecimal sumCredit = new BigDecimal(0);
        for (Transaction t : list) {
            if (t.getType().equals("D")) {
                if (t.getAmount() != null)
                    sumDebit = sumDebit.add(t.getAmount());
            } else if (t.getType().equals("C")) {
                if (t.getAmount() != null)
                    sumCredit = sumCredit.add(t.getAmount());
            }
        }
        return sumDebit.equals(sumCredit);
    }
}
