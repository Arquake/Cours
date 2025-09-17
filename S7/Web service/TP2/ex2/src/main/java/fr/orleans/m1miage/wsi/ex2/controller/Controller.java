package fr.orleans.m1miage.wsi.ex2.controller;

import fr.orleans.m1miage.wsi.ex2.dtos.*;
import fr.orleans.m1miage.wsi.ex2.exceptions.*;
import fr.orleans.m1miage.wsi.ex2.facade.FacadeUtilisateur;
import fr.orleans.m1miage.wsi.ex2.facade.FacadeVideo;
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
    ResponseEntity<Void> registerUser(@RequestBody UserDTO user) {
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
    ResponseEntity<Void> connexionUser(@RequestBody UserDTO user) {
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
    ResponseEntity<Void> addVideo(@RequestBody VideoDTO video, @RequestHeader("name") String name, @RequestHeader("password") String password) {
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

    @GetMapping("/user/video")
    ResponseEntity<Collection<UserVideoDTO>> getAllVideosFromUser(@RequestHeader("name") String name, @RequestHeader("password") String password) {
        UUID userId;
        try {
            userId = facadeUtilisateur.validationUtilisateur(name, password);
        } catch (ExceptionIncoherentUserInformations e) {
            return ResponseEntity.status(401).build();
        }

        Collection<Video> videos = facadeVideo.getVideoFromUser(userId);
        List<UserVideoDTO> videosDto = videos.stream().map(video -> new UserVideoDTO(video.getTitre(), video.getDecription(), video.getUrl(), video.getUuid().toString())).toList();
        return ResponseEntity.ok().body(videosDto);
    }

    @GetMapping("/video")
    ResponseEntity<Collection<UserVideoDTO>> getAllVideos() {
        Collection<Video> videos = facadeVideo.getAllVideos();
        Collection<UserVideoDTO> videosDto = videos.stream().map(video -> new UserVideoDTO(video.getTitre(), video.getDecription(), video.getUrl(), video.getUuid().toString())).toList();
        return ResponseEntity.ok().body(videosDto);
    }

    @GetMapping("/video/{id}")
    ResponseEntity<UserVideoDTO> getVideoById(@PathVariable UUID id) {
        Video video;
        try {
            video = facadeVideo.getVideo(id);
        } catch (ExceptionVideoNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserVideoDTO videoDto = new UserVideoDTO(video.getTitre(), video.getDecription(), video.getUrl(), video.getUuid().toString());
        return ResponseEntity.ok().body(videoDto);
    }

    /**
     * Crée une playlist
     * @param name le nom de l'utilisateur
     * @param password le pseudo de l'utilisateur
     * @param playlistInfos les informations attendu pour créer la playlist
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
     * @return
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
}
