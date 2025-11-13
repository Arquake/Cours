package veras.fr.tp3.model;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import veras.fr.tp3.exceptions.UtilisateurInexistantException;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final FacadeUtilisateurs facadeUtilisateurs;

    public CustomUserDetailsService(FacadeUtilisateurs facadeUtilisateurs) {
        this.facadeUtilisateurs = facadeUtilisateurs;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = facadeUtilisateurs.getUtilisateurByLogin(username);
        String role = "ADMIN";
        try {
            if (facadeUtilisateurs.isEtudiant(username)) {
                role = "USER";
            }
        } catch (UtilisateurInexistantException e) {
            throw new UsernameNotFoundException("kys");
        }
        return User.withUsername(user.getLogin()).password(user.getMotDePasse()).roles(role).build();
    }
}
