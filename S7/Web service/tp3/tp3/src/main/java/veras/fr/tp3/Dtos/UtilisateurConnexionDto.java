package veras.fr.tp3.Dtos;

import java.io.Serializable;

public record UtilisateurConnexionDto(String login, String password) implements Serializable {
}
