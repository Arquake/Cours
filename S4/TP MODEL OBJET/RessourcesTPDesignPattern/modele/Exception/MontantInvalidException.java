package modele.Exception;

public class MontantInvalidException extends Exception{
    public MontantInvalidException() {
        super("montant invalide");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
