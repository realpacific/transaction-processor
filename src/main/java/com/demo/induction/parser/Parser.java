package com.demo.induction.parser;

import java.util.List;

public interface Parser<T> {
    List<T> parse();
}