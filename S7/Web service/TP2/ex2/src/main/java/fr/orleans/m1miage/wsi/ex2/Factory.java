package fr.orleans.m1miage.wsi.ex2;

import fr.orleans.m1miage.wsi.ex2.dtos.PlaylistDTO;
import fr.orleans.m1miage.wsi.ex2.dtos.UserDTO;
import fr.orleans.m1miage.wsi.ex2.dtos.VideoDTO;
import fr.orleans.m1miage.wsi.ex2.modele.Playlist;
import fr.orleans.m1miage.wsi.ex2.modele.User;
import fr.orleans.m1miage.wsi.ex2.modele.Video;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class Factory {

    public Factory() {
    }

    public PlaylistDTO playlistDTOFactory(Playlist playlist) {
        return new PlaylistDTO(playlist.getUuid(), playlist.getVideos(), playlist.getPlaylistName());
    }

    public UserDTO userDTOFactory(User user, Collection<Video> videoByUser) {
        return new UserDTO(user.getNom(), user.getPlaylists(), videoByUser, user.getId());
    }

    public UserDTO userDTOFactory(User user) {
        return new UserDTO(user.getNom(), user.getPlaylists(), new ArrayList<>(), user.getId());
    }

    public VideoDTO videoDTOFactory(Video video) {
        return new VideoDTO(video.getTitre(), video.getDecription(), video.getUrl(), video.getUuid().toString(), video.getUserUUID().toString());
    }
}
