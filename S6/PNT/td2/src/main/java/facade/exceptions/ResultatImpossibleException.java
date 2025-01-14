package facade.exceptions;

public class ResultatImpossibleException extends Exception {
    public ResultatImpossibleException(String vainqueur) {
        super("le résultat "+vainqueur+ " est impossible.");
    }
}
