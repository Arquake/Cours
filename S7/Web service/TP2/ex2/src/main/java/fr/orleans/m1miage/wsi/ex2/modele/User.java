package fr.orleans.m1miage.wsi.ex2.modele;

import fr.orleans.m1miage.wsi.ex2.exceptions.ExceptionPlaylistNotFound;
import fr.orleans.m1miage.wsi.ex2.exceptions.ExceptionUserNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class User {

    private String nom;
    private String password;
    private UUID id;

    private final List<Playlist> playlists;

    User() {
        this.playlists = new ArrayList<>();
    }

    public User(String nom, String password) {
        this.nom = nom;
        this.password = password;
        this.id = UUID.randomUUID();
        this.playlists = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    /**
     * Crée une nouvelle playlist avec le nom donnée et retourne l'id de la dite playlist
     * @param name le nom de la playlist
     * @return l'id de la playlist
     */
    public UUID newPlaylist(String name) {
        Playlist playlist = new Playlist(name);
        playlists.add(playlist);
        return playlist.getUuid();
    }

    public void addVideoToPlaylist(UUID uuid, Video video) throws ExceptionPlaylistNotFound {
        if (playlists.stream().noneMatch(p -> p.getUuid().equals(uuid))) {
            throw new ExceptionPlaylistNotFound();
        }
        playlists.stream().filter(p -> p.getUuid().equals(uuid)).findFirst().ifPresent(p -> {p.addVideo(video);});
    }
}
