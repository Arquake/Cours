package fr.miage.orleans.mac.exceptions;

public class QuitException extends Exception {
    public QuitException() {
        super("Le joueur quitte le jeu");
    }
}
