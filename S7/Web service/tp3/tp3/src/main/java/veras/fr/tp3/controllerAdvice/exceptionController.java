package veras.fr.tp3.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import veras.fr.tp3.exceptions.AccesIllegalAUneQuestionException;
import veras.fr.tp3.exceptions.LoginDejaUtiliseException;
import veras.fr.tp3.exceptions.QuestionInexistanteException;
import veras.fr.tp3.exceptions.UtilisateurInexistantException;

@ControllerAdvice
public class exceptionController extends Exception {

    @ExceptionHandler({
            UtilisateurInexistantException.class,
            QuestionInexistanteException.class,
            UtilisateurInexistantException.class
    })
    public ResponseEntity<String> notFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({
            LoginDejaUtiliseException.class
    })
    public ResponseEntity<String> conflict(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler({
            AccesIllegalAUneQuestionException.class
    })
    public ResponseEntity<String> forbidden(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
