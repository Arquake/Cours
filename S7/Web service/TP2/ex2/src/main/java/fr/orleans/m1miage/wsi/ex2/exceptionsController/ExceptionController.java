package fr.orleans.m1miage.wsi.ex2.exceptionsController;

import fr.orleans.m1miage.wsi.ex2.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={ExceptionUserAlreadyExist.class})
    protected ResponseEntity<String> handleConflict(Exception e, WebRequest req) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(value={
            ExceptionInvalidUserData.class,
            ExceptionVideoInvalidInformations.class
    })
    protected ResponseEntity<String> handleBadRequest(Exception e, WebRequest res) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value={ExceptionIncoherentUserInformations.class})
    protected ResponseEntity<String> handleBadUserInfo(Exception e, WebRequest req) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(value = {
            ExceptionUserNotFound.class,
            ExceptionVideoNotFound.class,
            ExceptionVideoNotFound.class,
            ExceptionPlaylistNotFound.class
    })
    protected ResponseEntity<String> handleNotFound(Exception e, WebRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
