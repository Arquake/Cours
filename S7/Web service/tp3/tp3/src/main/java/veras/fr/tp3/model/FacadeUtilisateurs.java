package veras.fr.tp3.model;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import veras.fr.tp3.config.CryptoConfig;
import veras.fr.tp3.exceptions.LoginDejaUtiliseException;
import veras.fr.tp3.exceptions.UtilisateurInexistantException;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacadeUtilisateurs {

    /** Stocke l'ensemble des utilisateurs inscrits */
    private Map<String,Utilisateur> utilisateursMap;

    private final CryptoConfig cryptoConfig;

    public FacadeUtilisateurs(CryptoConfig cryptoConfig) {
        utilisateursMap = new HashMap<>();
        this.cryptoConfig = cryptoConfig;
    }

    /**
     * Retourne l'identifiant d'un utilisateur, d'après son login (c.-à-d. son adresse email).
     * @param login le login de l'utilisateur dont on veut l'identifiant
     * @return l'identifiant de l'utilisateur.
     * @throws UtilisateurInexistantException s'il n'existe pas d'utilisateur avec ce login
     */
    public int getUtilisateurIntId(String login) throws UtilisateurInexistantException {
        if (utilisateursMap.containsKey(login)) {
            return this.utilisateursMap.get(login).getIdUtilisateur();
        } else {
            throw new UtilisateurInexistantException();
        }
    }

    /**
     * Retourne un utilisateur, d'après son login.
     * @param login le login de l'utilisateur dont on veut l'identifiant
     * @return l'utilisateur.
     * @throws UtilisateurInexistantException s'il n'existe pas d'utilisateur avec ce login
     */
    public Utilisateur getUtilisateurByLogin(String login) throws UtilisateurInexistantException {
        return utilisateursMap.get(login);
    }

    /**
     * Inscrit un nouvel utilisateur.
     * @param login      le login de l'utilisateur (c.-à-d. son adresse email)
     * @param motDePasse le mot de passe de l'utilisateur
     * @return l'identifiant de l'utilisateur inscrit.
     * @throws LoginDejaUtiliseException si le login est déjà utilisé par un autre utilisateur
     */
    public int inscrireUtilisateur(String login, String motDePasse) throws LoginDejaUtiliseException {
        if (utilisateursMap.containsKey(login)) {
            throw new LoginDejaUtiliseException();
        } else {
            String encryptedPassword = cryptoConfig.passwordEncoder().encode(motDePasse);
            Utilisateur utilisateur = new Utilisateur(login, encryptedPassword);
            utilisateursMap.put(utilisateur.getLogin(), utilisateur);
            return utilisateur.getIdUtilisateur();
        }
    }

    /**
     * Vérifie si un mot de passe est correct (devient inutile dans la version finale).
     * @param login      le login de l'utilisateur
     * @param motDePasse le mot de passe à vérifier
     * @return _true_ si le mot de passe est correct, _false_ sinon.
     */
    public boolean verifierMotDePasse(String login, String motDePasse) {
        if (utilisateursMap.containsKey(login)) {
            String encryptedPassword = utilisateursMap.get(login).getMotDePasse();
            return cryptoConfig.passwordEncoder().matches(motDePasse, encryptedPassword);
        } else {
            return false;
        }
    }
}