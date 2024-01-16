package exo3;

public class EmpruntImpossibleException extends RuntimeException{

    public EmpruntImpossibleException() {
        super("Emprunt Impossible");
    }

    public EmpruntImpossibleException(String message) {
        super(message);
    }

    public EmpruntImpossibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmpruntImpossibleException(Throwable cause) {
        super(cause);
    }

    public EmpruntImpossibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
