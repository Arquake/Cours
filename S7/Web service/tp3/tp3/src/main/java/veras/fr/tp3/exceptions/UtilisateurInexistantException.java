package veras.fr.tp3.exceptions;

public class UtilisateurInexistantException extends Exception {

    public UtilisateurInexistantException() {
        super("L'utilisateur n'existe pas");
    }
}
