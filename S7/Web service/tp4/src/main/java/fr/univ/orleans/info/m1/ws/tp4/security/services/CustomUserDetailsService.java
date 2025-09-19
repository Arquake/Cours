package fr.univ.orleans.info.m1.ws.tp4.security.services;

import fr.univ.orleans.info.m1.ws.tp4.modele.FacadeUtilisateurs;
import fr.univ.orleans.info.m1.ws.tp4.modele.Role;
import fr.univ.orleans.info.m1.ws.tp4.modele.Utilisateur;
import fr.univ.orleans.info.m1.ws.tp4.modele.exceptions.UtilisateurInexistantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    // Injection de dépendance via le constructeur (l'injection via @Autowired sur attribut est dépréciée)
    private final FacadeUtilisateurs facadeUtilisateurs;

    public CustomUserDetailsService(@Autowired FacadeUtilisateurs facadeUtilisateurs) {
        this.facadeUtilisateurs = facadeUtilisateurs;
    }

    /**
     * Génération dynamique des détails d'authentification d'un utilisateur, selon son username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur;
        try {
            utilisateur = facadeUtilisateurs.getUtilisateurByEmail(username);
        } catch (UtilisateurInexistantException e) {
            throw new UsernameNotFoundException(username);
        }

        String[] roles = getRoles(utilisateur.getEmail());

        return User.builder()
                .username(utilisateur.getEmail())
                .password(utilisateur.getEncodedPassword())
                .roles(roles)
                .build();
    }

    /**
     * Retourne les rôles d'un utilisateur, d'après son e-mail.
     */
    private static String[] getRoles(String email) {
        String domain = (email.split("@"))[1];

        return switch (domain) {
            case "etu.univ-orleans.fr" -> new String[]{
                    Role.ETUDIANT.name()
            };
            case "univ-orleans.fr" -> new String[]{
                    Role.ETUDIANT.name(),
                    Role.ENSEIGNANT.name()
            };
            default -> new String[0];
        };
    }

}
