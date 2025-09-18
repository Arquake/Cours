package fr.orleans.m1miage.wsi.ex2.controller;

import fr.orleans.m1miage.wsi.ex2.dtos.*;
import fr.orleans.m1miage.wsi.ex2.exceptions.*;
import fr.orleans.m1miage.wsi.ex2.facade.FacadeUtilisateur;
import fr.orleans.m1miage.wsi.ex2.facade.FacadeVideo;
import fr.orleans.m1miage.wsi.ex2.modele.Playlist;
import fr.orleans.m1miage.wsi.ex2.modele.User;
import fr.orleans.m1miage.wsi.ex2.modele.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    FacadeUtilisateur facadeUtilisateur;

    @Autowired
    FacadeVideo facadeVideo;

    @PostMapping("/registration")
    ResponseEntity<Void> registerUser(@RequestBody UserConnexionDTO user) {
        if (Objects.isNull(user)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            UUID id = facadeUtilisateur.inscriptionUtilisateur(user.getName(), user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/api/user/"+id).build();
        } catch (ExceptionInvalidUserData | ExceptionUserAlreadyExist e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/connexion")
    ResponseEntity<Void> connexionUser(@RequestBody UserConnexionDTO user) {
        if (Objects.isNull(user)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            facadeUtilisateur.validationUtilisateur(user.getName(), user.getPassword());
            return ResponseEntity.ok().build();
        } catch (ExceptionIncoherentUserInformations e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/video")
    ResponseEntity<Void> addVideo(@RequestBody VideoCreationDTO video, @RequestHeader("name") String name, @RequestHeader("password") String password) {
        UUID userId;
        try {
            userId = facadeUtilisateur.validationUtilisateur(name, password);
        } catch (ExceptionIncoherentUserInformations e) {
            return ResponseEntity.status(401).build();
        }

        try {
            UUID uuid = facadeVideo.ajouterVideo(video.getTitre(), video.getDescription(), video.getUrl(), userId);
            return ResponseEntity.status(201).header("Location", "/api/video/"+uuid.toString()).build();
        } catch (ExceptionVideoInvalidInformations e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/video")
    ResponseEntity<Collection<VideoDTO>> getAllVideos() {
        Collection<Video> videos = facadeVideo.getAllVideos();
        Collection<VideoDTO> videosDto = videos.stream().map(video -> new VideoDTO(video.getTitre(), video.getDecription(), video.getUrl(), video.getUuid().toString())).toList();
        return ResponseEntity.ok().body(videosDto);
    }

    @GetMapping("/video/{id}")
    ResponseEntity<VideoDTO> getVideoById(@PathVariable UUID id) {
        Video video;
        try {
            video = facadeVideo.getVideo(id);
        } catch (ExceptionVideoNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        VideoDTO videoDto = new VideoDTO(video.getTitre(), video.getDecription(), video.getUrl(), video.getUuid().toString());
        return ResponseEntity.ok().body(videoDto);
    }

    @GetMapping("/user/video")
    ResponseEntity<Collection<VideoDTO>> getAllVideosFromUser(@RequestHeader("name") String name, @RequestHeader("password") String password) {
        UUID userId;
        try {
            userId = facadeUtilisateur.validationUtilisateur(name, password);
        } catch (ExceptionIncoherentUserInformations e) {
            return ResponseEntity.status(401).build();
        }

        Collection<Video> videos = facadeVideo.getVideoFromUser(userId);
        List<VideoDTO> videosDto = videos.stream().map(video -> new VideoDTO(video.getTitre(), video.getDecription(), video.getUrl(), video.getUuid().toString())).toList();
        return ResponseEntity.ok().body(videosDto);
    }

    /**
     * Récupère et retourne les informations liés à l'utilisateur
     * @param name le login de l'utilisateur
     * @param password le mdp de l'utilisateur
     * @return Les informations de l'utilisateur
     */
    @GetMapping("/user")
    ResponseEntity<UserDTO> getOneself(@RequestHeader("name") String name, @RequestHeader("password") String password) {
        UUID userId;
        try {
            userId = facadeUtilisateur.validationUtilisateur(name, password);
        } catch (ExceptionIncoherentUserInformations e) {
            return ResponseEntity.status(401).build();
        }
        User user;
        try {
            user = facadeUtilisateur.getUserById(userId);
        } catch (ExceptionUserNotFound e) {
            return ResponseEntity.badRequest().build();
        }

        Collection<Video> videos = facadeVideo.getVideoFromUser(userId);

        UserDTO userDto = new UserDTO(user.getNom(), user.getPlaylists(), videos);

        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/user/playlist")
    ResponseEntity<UserPlaylistsDTO> getPlaylistByUser(@RequestHeader("name") String name, @RequestHeader("password") String password) {
        UUID userId;
        try {
            userId = facadeUtilisateur.validationUtilisateur(name, password);
        } catch (ExceptionIncoherentUserInformations e) {
            return ResponseEntity.status(401).build();
        }

        Collection<Playlist> playlists;
        try {
            playlists = facadeUtilisateur.getPlaylistsByUser(userId);
        } catch (ExceptionUserNotFound e) {
            return ResponseEntity.status(400).build();
        }
        UserPlaylistsDTO userPlaylistsDto = new UserPlaylistsDTO(playlists);
        return ResponseEntity.ok().body(userPlaylistsDto);
    }

    /**
     * Crée une playlist
     * @param name le nom de l'utilisateur
     * @param password le pseudo de l'utilisateur
     * @param playlistInfos les informations attendues pour créer la playlist
     * @return La localisation de la playlist
     */
    @PostMapping("/user/playlist")
    ResponseEntity<Void> createPlaylist(@RequestHeader("name") String name, @RequestHeader("password") String password, @RequestBody PlaylistCreationDTO playlistInfos) {
        UUID userId;
        try {
            userId = facadeUtilisateur.validationUtilisateur(name, password);
        } catch (ExceptionIncoherentUserInformations e) {
            return ResponseEntity.status(401).build();
        }

        UUID playlistId;

        try {
            playlistId = facadeUtilisateur.createPlaylistForUser(userId, playlistInfos.getName());
        } catch (ExceptionUserNotFound e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/api/user/playlist/"+playlistId).build();
    }

    /**
     * Ajoute une vidéo à la playlist donnée dans l'url
     * @param playlistId l'id de la playlist
     * @param name le nom du user
     * @param password le mdp du user
     * @param videoDto les informations utiles à l'ajout de la video dans la playlist
     * @return nothing
     */
    @PostMapping("/user/playlist/{playlistId}/video")
    ResponseEntity<Void> addVideoToPlaylist(@PathVariable UUID playlistId, @RequestHeader("name") String name, @RequestHeader("password") String password, @RequestBody PlaylistVideoAdditionDTO videoDto) {
        UUID userId;
        try {
            userId = facadeUtilisateur.validationUtilisateur(name, password);
        } catch (ExceptionIncoherentUserInformations e) {
            return ResponseEntity.status(401).build();
        }

        Video video;
        try {
            video = facadeVideo.getVideo(videoDto.getVideoId());
        } catch (ExceptionVideoNotFound e) {
            return ResponseEntity.badRequest().build();
        }

        try {
            facadeUtilisateur.addVideoToPlaylist(userId, playlistId, video);
        } catch (ExceptionUserNotFound | ExceptionPlaylistNotFound e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Supprime une video de la playlist de l'utilisateur
     * @param name le login de l'utilisateur
     * @param password le mdp de l'utilisateur
     * @param idPlaylist l'id de la playlist de l'utilisateur
     * @param idVideo l'id de la video dans la playlist à supprimer
     * @return rien
     */
    @DeleteMapping("/user/playlist/{idPlaylist}/video/{idVideo}")
    ResponseEntity<Void> deleteVideoFromPlaylist(@RequestHeader("name") String name, @RequestHeader("password") String password, @PathVariable UUID idPlaylist, @PathVariable UUID idVideo) {
        UUID userId;
        try {
            userId = facadeUtilisateur.validationUtilisateur(name, password);
        } catch (ExceptionIncoherentUserInformations e) {
            return ResponseEntity.status(401).build();
        }

        try {
            facadeUtilisateur.removeVideoFromUserPlaylist(idPlaylist, userId, idVideo);
        } catch (ExceptionUserNotFound | ExceptionPlaylistNotFound | ExceptionVideoNotFound e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
