package org.example.exceptions;

public class ExceptionTooManyArguments extends Exception {
    public ExceptionTooManyArguments() {
        super("Too many arguments were given");
    }
}
