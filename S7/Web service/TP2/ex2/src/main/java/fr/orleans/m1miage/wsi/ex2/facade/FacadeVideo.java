package fr.orleans.m1miage.wsi.ex2.facade;

import fr.orleans.m1miage.wsi.ex2.exceptions.ExceptionVideoInvalidInformations;
import fr.orleans.m1miage.wsi.ex2.modele.Video;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FacadeVideo {

    Map<UUID, Video> videos;

    public FacadeVideo() {
        this.videos = new HashMap<>();
    }

    public UUID ajouterVideo(String titre, String description, String url, UUID userId) throws ExceptionVideoInvalidInformations {
        if (titre.isBlank() || description.isBlank() || url.isBlank()) { throw new ExceptionVideoInvalidInformations(); }
        Video video = new Video(url, description, titre, userId);
        videos.put(video.getUuid(), video);
        return video.getUuid();
    }

    public Collection<Video> getVideoFromUser(UUID userId) {
        return videos.keySet().stream().filter(uuid -> videos.get(uuid).getUserUUID().equals(userId)).map(uuid -> videos.get(uuid)).toList();
    }

    public Collection<Video> getAllVideos() {
        return videos.values();
    }

    public Video getVideo(UUID id) {
        return videos.get(id);
    }
}
