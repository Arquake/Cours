package fr.orleans.m1miage.wsi.ex2.exceptions;

public class ExceptionVideoNotFound extends Exception {
    public ExceptionVideoNotFound() {
        super("The video does not exist");
    }
}
