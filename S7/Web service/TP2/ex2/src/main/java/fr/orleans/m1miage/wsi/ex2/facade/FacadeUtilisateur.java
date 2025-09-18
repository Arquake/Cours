package fr.orleans.m1miage.wsi.ex2.facade;

import fr.orleans.m1miage.wsi.ex2.exceptions.*;
import fr.orleans.m1miage.wsi.ex2.modele.Playlist;
import fr.orleans.m1miage.wsi.ex2.modele.User;
import fr.orleans.m1miage.wsi.ex2.modele.Video;
import org.springframework.stereotype.Component;

import java.util.*;

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

    /**
     * Pour l'id d'un user donné et le nom de playlist, crée une playlist lié à l'utilisateur avec ce nom
     * @param uuid l'id utilisateur
     * @param playlistName le nom de la playlist
     * @return l'id de la playlist
     * @throws ExceptionUserNotFound l'utilisateur n'existe pas
     */
    public UUID createPlaylistForUser(UUID uuid, String playlistName) throws ExceptionUserNotFound {
        if (!utilisateurs.containsKey(uuid)) { throw new ExceptionUserNotFound(); }
        return utilisateurs.get(uuid).newPlaylist(playlistName);
    }


    public void addVideoToPlaylist(UUID userId, UUID playlistId, Video video) throws ExceptionUserNotFound, ExceptionPlaylistNotFound {
        if (!utilisateurs.containsKey(userId)) { throw new ExceptionUserNotFound(); }
        User user = utilisateurs.get(userId);
        user.addVideoToPlaylist(playlistId, video);
    }

    public User getUserById(UUID uuid) throws ExceptionUserNotFound {
        if (!utilisateurs.containsKey(uuid)) { throw new ExceptionUserNotFound(); }
        return utilisateurs.get(uuid);
    }

    public Collection<Playlist> getPlaylistsByUser(UUID id) throws ExceptionUserNotFound {
        if (!utilisateurs.containsKey(id)) { throw new ExceptionUserNotFound(); }
        return utilisateurs.get(id).getPlaylists();
    }

    public void removeVideoFromUserPlaylist(UUID playlistId, UUID userId, UUID videoId) throws ExceptionUserNotFound, ExceptionPlaylistNotFound, ExceptionVideoNotFound {
        if (!utilisateurs.containsKey(userId)) { throw new ExceptionUserNotFound(); }
        User user = utilisateurs.get(userId);
        user.removeVideoFromPlaylist(playlistId, videoId);
    }
}
