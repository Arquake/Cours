package fr.orleans.m1miage.wsi.ex2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionUserAlreadyExist extends Exception {
    public ExceptionUserAlreadyExist() {
        super("Un utilisateur avec des crédentiels identique existe déjà");
    }
}
