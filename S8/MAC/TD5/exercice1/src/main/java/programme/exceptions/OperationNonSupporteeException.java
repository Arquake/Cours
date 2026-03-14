package programme.exceptions;

public class OperationNonSupporteeException extends Exception {
    public OperationNonSupporteeException() {
        super("Opération non supporté");
    }
}
