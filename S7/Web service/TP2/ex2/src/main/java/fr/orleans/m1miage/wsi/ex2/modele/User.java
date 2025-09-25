package fr.orleans.m1miage.wsi.ex2.modele;

import fr.orleans.m1miage.wsi.ex2.exceptions.ExceptionPlaylistNotFound;
import fr.orleans.m1miage.wsi.ex2.exceptions.ExceptionUserNotFound;
import fr.orleans.m1miage.wsi.ex2.exceptions.ExceptionVideoNotFound;

import java.util.*;

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
    public Playlist newPlaylist(String name) {
        Playlist playlist = new Playlist(name);
        playlists.add(playlist);
        return playlist;
    }

    public void addVideoToPlaylist(UUID uuid, Video video) throws ExceptionPlaylistNotFound {
        if (playlists.stream().noneMatch(p -> p.getUuid().equals(uuid))) {
            throw new ExceptionPlaylistNotFound();
        }
        playlists.stream().filter(p -> p.getUuid().equals(uuid)).findFirst().ifPresent(p -> {
            System.out.println(p);p.addVideo(video);});
    }

    public Collection<Playlist> getPlaylists() {
        return playlists;
    }

    public void removeVideoFromPlaylist(UUID playlistId, UUID videoId) throws ExceptionPlaylistNotFound, ExceptionVideoNotFound {
        if (playlists.stream().noneMatch(p -> p.getUuid().equals(playlistId))) { throw new ExceptionPlaylistNotFound(); }
        final Playlist[] playlist = new Playlist[1];
        playlists.stream().filter(p -> p.getUuid().equals(playlistId)).findFirst().ifPresent(p -> {
            playlist[0] = p;
        });
        if (playlist[0].getVideos().stream().noneMatch(v -> v.getUuid().equals(videoId))) {
            throw new ExceptionVideoNotFound();
        }
        playlist[0].removeVideo(videoId);
    }
}
