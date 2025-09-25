package fr.orleans.m1miage.wsi.ex2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionVideoInvalidInformations extends Exception {
    public ExceptionVideoInvalidInformations() {
        super("Les arguments donnés sont invalides ou incomplet");
    }
}
