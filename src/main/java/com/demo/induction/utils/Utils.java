package com.demo.induction.utils;

import java.util.List;

public class Utils {

    public static void printDecorator(String s) {
        System.out.println("---------- " + s + " --------------");
    }

    public static <T> void printList(List<T> list) {
        for (T t : list) {
            System.out.println(t.toString());
        }
    }
}
