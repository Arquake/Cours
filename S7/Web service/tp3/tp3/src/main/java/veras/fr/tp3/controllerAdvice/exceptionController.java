package veras.fr.tp3.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import veras.fr.tp3.exceptions.UtilisateurInexistantException;

@ControllerAdvice
public class exceptionController extends Exception {

    @ExceptionHandler({
            UtilisateurInexistantException.class
    })
    public ResponseEntity<String> notFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
