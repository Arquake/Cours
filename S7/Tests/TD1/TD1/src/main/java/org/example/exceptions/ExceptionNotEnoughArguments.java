package org.example.exceptions;

public class ExceptionNotEnoughArguments extends Exception {
    public ExceptionNotEnoughArguments() {
        super("Not enough arguments were given");
    }
}
