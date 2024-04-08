package modele.Exception;

public class OperationInvalidException extends Exception{
    public OperationInvalidException() {
        super("opération invalide");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
