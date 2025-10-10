package veras.fr.tp3.exceptions;

public class AccesIllegalAUneQuestionException extends Exception {
    public AccesIllegalAUneQuestionException() {
        super("Accès interdit à la ressource");
    }
}
