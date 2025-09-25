package fr.orleans.m1miage.wsi.ex2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionIncoherentUserInformations extends Exception {
    public ExceptionIncoherentUserInformations() {
        super("Les crédentiels sont invalides");
    }
}
