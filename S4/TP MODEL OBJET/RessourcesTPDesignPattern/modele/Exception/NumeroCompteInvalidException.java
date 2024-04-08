package modele.Exception;

public class NumeroCompteInvalidException extends Exception{
    public NumeroCompteInvalidException() {
        super("numero de compte invalide");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
