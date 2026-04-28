package org.example.model.state;

public class NotEnoughCafeException extends Exception {

    public NotEnoughCafeException() {
        super("Pas assez de café");
    }
}
