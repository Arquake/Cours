package fr.orleans.m1miage.wsi.ex2.exceptions;

public class ExceptionUserAlreadyExist extends Exception {
    public ExceptionUserAlreadyExist() {
        super("L'utilisateur existe déjà");
    }
}
