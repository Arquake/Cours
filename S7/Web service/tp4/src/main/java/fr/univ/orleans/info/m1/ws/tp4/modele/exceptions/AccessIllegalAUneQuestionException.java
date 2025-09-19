package fr.univ.orleans.info.m1.ws.tp4.modele.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessIllegalAUneQuestionException extends Exception {
}
