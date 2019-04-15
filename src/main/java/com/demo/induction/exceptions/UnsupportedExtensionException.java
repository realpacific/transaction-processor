package com.demo.induction.exceptions;

public class UnsupportedExtensionException extends RuntimeException {
    public UnsupportedExtensionException() {
        super("Unsupported file format.");
    }
}
