package fr.orleans.m1miage.wsi.ex2.dtos;

import fr.orleans.m1miage.wsi.ex2.modele.Playlist;

import java.io.Serializable;
import java.util.Collection;

public class UserPlaylistsDTO implements Serializable {
    public UserPlaylistsDTO() {
    }

    public UserPlaylistsDTO(Collection<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Collection<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Collection<Playlist> playlists) {
        this.playlists = playlists;
    }

    private Collection<Playlist> playlists;
}
