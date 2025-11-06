package veras.fr.tp3.factory;

import org.springframework.stereotype.Service;
import veras.fr.tp3.Dtos.UtilisateurDTO;
import veras.fr.tp3.model.Utilisateur;

@Service
public class UtilisateurFactory {

    public UtilisateurFactory() {
    }

    public static UtilisateurDTO createUtilisateurDtoWithUtilisateur(Utilisateur user) {
        return new UtilisateurDTO(user.getIdUtilisateur(), user.getLogin());
    }
}
