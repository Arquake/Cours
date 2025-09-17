package fr.orleans.m1miage.wsi.ex2.dtos;

import java.io.Serializable;
import java.util.UUID;

public class PlaylistVideoAdditionDTO implements Serializable {
    private UUID videoId;

    public UUID getVideoId() {
        return videoId;
    }

    public void setVideoId(UUID videoId) {
        this.videoId = videoId;
    }
}
