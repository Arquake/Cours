package fr.orleans.m1miage.wsi.ex2.facade;

import fr.orleans.m1miage.wsi.ex2.exceptions.ExceptionIncoherentUserInformations;
import fr.orleans.m1miage.wsi.ex2.exceptions.ExceptionInvalidUserData;
import fr.orleans.m1miage.wsi.ex2.exceptions.ExceptionUserAlreadyExist;
import fr.orleans.m1miage.wsi.ex2.modele.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
public class FacadeUtilisateur {

    private Map<UUID, User> utilisateurs;

    FacadeUtilisateur() {
        utilisateurs = new HashMap<>();
    }

    /**
     * Permet à un utilisateur de s'inscrire
     * @param name le nom de l'utilisateur
     * @param password le mot de passe de l'utilisateur
     * @throws ExceptionInvalidUserData Si le name ou password est vide ou contient des espaces
     * @throws ExceptionUserAlreadyExist Si un utilisateur avec ce nom existe déjà
     */
    public UUID inscriptionUtilisateur(String name, String password) throws ExceptionInvalidUserData, ExceptionUserAlreadyExist {
        if (name.isBlank() || name.contains(" ") || password.isBlank() ||password.contains(" ")) { throw new ExceptionInvalidUserData(); }
        boolean alreadyPresent = utilisateurs.keySet().stream().filter(uuid -> utilisateurs.get(uuid).getNom().equals(name)).count() == 1;
        if (alreadyPresent) { throw new ExceptionUserAlreadyExist(); }

        User newUser = new User(name, password);
        utilisateurs.put(newUser.getId(), newUser);
        return newUser.getId();
    }

    /**
     * Vérifie si le nom donnée existe bien et que son mot de passe corresponde avec celui connu
     * @param name le nom du compte
     * @param password le mot de passe
     * @throws ExceptionIncoherentUserInformations Si les informations sont invalides
     */
    public UUID validationUtilisateur(String name, String password) throws ExceptionIncoherentUserInformations {
        if (utilisateurs.isEmpty()) { throw new ExceptionIncoherentUserInformations(); }
        List<UUID> userSearch = utilisateurs.keySet().stream().filter(uuid -> utilisateurs.get(uuid).getNom().equals(name)).toList();
        if (userSearch.isEmpty()) { throw new ExceptionIncoherentUserInformations(); }
        User user = utilisateurs.get(userSearch.getFirst());
        if (!user.getPassword().equals(password)) {
            throw new ExceptionIncoherentUserInformations();
        }
        return user.getId();
    }
}
