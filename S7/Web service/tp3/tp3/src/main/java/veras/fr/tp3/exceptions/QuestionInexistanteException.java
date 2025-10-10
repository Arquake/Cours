package veras.fr.tp3.exceptions;

public class QuestionInexistanteException extends Exception {
    public QuestionInexistanteException() {
        super("La question n'existe pas");
    }
}
