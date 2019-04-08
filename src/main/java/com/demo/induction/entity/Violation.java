package com.demo.induction.entity;

public class Violation {
    private int order;
    private String property;
    private String description;

    public Violation(int order, String property, String description) {
        this.order = order;
        this.property = property;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Violation{" +
                "order=" + order +
                ", property='" + property + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
