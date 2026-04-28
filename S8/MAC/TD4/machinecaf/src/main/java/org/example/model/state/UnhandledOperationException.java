package org.example.model.state;

public class UnhandledOperationException extends Exception {

    public UnhandledOperationException() {
        super("Cette action ne peut pas être traité avec l'état actuel");
    }
}
