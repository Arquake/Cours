package fr.orleans.m1miage.wsi.ex2.exceptions;

public class ExceptionPlaylistNotFound extends Exception {
    public ExceptionPlaylistNotFound() {
        super("La playlist n'existe pas");
    }
}
