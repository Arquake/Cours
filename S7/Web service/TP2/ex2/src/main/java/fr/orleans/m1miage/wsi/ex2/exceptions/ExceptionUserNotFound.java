package fr.orleans.m1miage.wsi.ex2.exceptions;

public class ExceptionUserNotFound extends Exception {
    public ExceptionUserNotFound() {
        super("L'utilisateur n'existe pas");
    }
}
