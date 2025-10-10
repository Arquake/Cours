package veras.fr.tp3.Dtos;

import java.io.Serializable;

public record UtilisateurCreationDTO(String login, String password) implements Serializable {
}
