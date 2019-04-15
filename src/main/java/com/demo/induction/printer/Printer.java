package com.demo.induction.printer;

import org.springframework.stereotype.Service;

@Service
public interface Printer<T> {
    void print(T t);
}
