package facade.exceptions;

public class UtilisateurNonAdminException extends Exception {
    public UtilisateurNonAdminException(String login) {
        super("l'utilisateur "+login+" n'a pas les droits d'admin");
    }
}
