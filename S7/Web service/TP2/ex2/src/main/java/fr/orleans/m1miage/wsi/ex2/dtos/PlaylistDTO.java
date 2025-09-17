package fr.orleans.m1miage.wsi.ex2.dtos;

import fr.orleans.m1miage.wsi.ex2.modele.Video;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class PlaylistDTO implements Serializable {
    private UUID uuid;
    private String name;
    private List<Video> videos;

    public PlaylistDTO() {
    }

    public PlaylistDTO(UUID uuid, List<Video> videos, String name) {
        this.uuid = uuid;
        this.videos = videos;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
