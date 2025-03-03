package facadeCreaFilm;

public class NomFilmDejaExistantException extends Throwable {

    public NomFilmDejaExistantException() {
    }

    public NomFilmDejaExistantException(String message) {
        super(message);
    }
}
