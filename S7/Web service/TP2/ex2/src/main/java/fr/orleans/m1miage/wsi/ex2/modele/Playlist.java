package fr.orleans.m1miage.wsi.ex2.modele;

import java.util.List;
import java.util.UUID;

public class Playlist {

    private UUID uuid;

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    private String playlistName = "";
    private List<Video> videos;

    public Playlist(String name) {
        this.playlistName = name;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void addVideo(Video video) {
        videos.add(video);
    }

    public void removeVideo(UUID id) {
        videos.removeIf(video -> video.getUuid().equals(id));
    }
}
