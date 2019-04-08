package com.demo.induction.entity;

import java.math.BigDecimal;

public class Transaction {
    private String type;
    private BigDecimal amount;
    private String narration;

    public Transaction(String type, BigDecimal amount, String narration) {
        this.type = type;
        this.amount = amount;
        this.narration = narration;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    public String getNarration() {
        return narration;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", narration='" + narration + '\'' +
                '}';
    }
}
