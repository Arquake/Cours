package fr.orleans.m1miage.wsi.ex2.dtos;

import fr.orleans.m1miage.wsi.ex2.modele.Playlist;
import fr.orleans.m1miage.wsi.ex2.modele.Video;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserDTO implements Serializable {

    private String name;
    private Collection<Playlist> playlists;
    private Collection<Video> videos;

    public UserDTO(String name, Collection<Playlist> playlists, Collection<Video> videos) {
        this.name = name;
        this.playlists = playlists;
        this.videos = videos;
    }

    public UserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Playlist> getPlaylists() {
        return playlists;
    }
    public void setPlaylists(Collection<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Collection<Video> getVideos() {
        return videos;
    }

    public void setVideos(Collection<Video> videos) {
        this.videos = videos;
    }
}
