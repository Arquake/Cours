package fr.orleans.m1miage.wsi.ex2.controller;

import fr.orleans.m1miage.wsi.ex2.Factory;
import fr.orleans.m1miage.wsi.ex2.dtos.*;
import fr.orleans.m1miage.wsi.ex2.exceptions.*;
import fr.orleans.m1miage.wsi.ex2.facade.FacadeUtilisateur;
import fr.orleans.m1miage.wsi.ex2.facade.FacadeVideo;
import fr.orleans.m1miage.wsi.ex2.modele.Playlist;
import fr.orleans.m1miage.wsi.ex2.modele.User;
import fr.orleans.m1miage.wsi.ex2.modele.Video;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("/api")
public class Controller {

    FacadeUtilisateur facadeUtilisateur;

    FacadeVideo facadeVideo;

    Factory factory;

    public Controller(FacadeUtilisateur facadeUtilisateur, FacadeVideo facadeVideo, Factory factory) {
        this.facadeUtilisateur = facadeUtilisateur;
        this.facadeVideo = facadeVideo;
        this.factory = factory;
    }

    /**
     * permet au user de créer un compte
     * @param user les données relative à la création du compte user
     * @return le user créé
     * @throws ExceptionInvalidUserData
     * @throws ExceptionUserAlreadyExist
     */
    @PostMapping("/registration")
    ResponseEntity<UserDTO> registerUser(@RequestBody UserConnexionDTO user, UriComponentsBuilder builder) throws ExceptionInvalidUserData, ExceptionUserAlreadyExist {
        if (Objects.isNull(user)) {
            throw new ExceptionInvalidUserData();
        }
        User newUser = facadeUtilisateur.inscriptionUtilisateur(user.getName(), user.getPassword());

        UserDTO dto = factory.userDTOFactory(newUser);

        String path = builder.path("/api/user/{id}").buildAndExpand(dto.getId()).toUriString();
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", path).body(dto);
    }

    /**
     * permet au user de s'authentifier
     * @param user les données du user
     * @return le user
     * @throws ExceptionIncoherentUserInformations
     * @throws ExceptionUserNotFound
     */
    @PostMapping("/connexion")
    ResponseEntity<UserDTO> connexionUser(@RequestBody UserConnexionDTO user) throws ExceptionIncoherentUserInformations, ExceptionUserNotFound {
        if (Objects.isNull(user)) {
            throw new ExceptionIncoherentUserInformations();
        }

        UUID userId = facadeUtilisateur.validationUtilisateur(user.getName(), user.getPassword());
        User currentUser = facadeUtilisateur.getUserById(userId);
        UserDTO userDto = factory.userDTOFactory(currentUser, facadeVideo.getVideoFromUser(userId));

        return ResponseEntity.ok().body(userDto);
    }

    /**
     * permet au user de créer une vidéo rattaché à son compte
     * @param video la video à créer
     * @param name le login du user
     * @param password le mdp du user
     * @return la vidéo créé
     * @throws ExceptionIncoherentUserInformations
     * @throws ExceptionVideoInvalidInformations
     */
    @PostMapping("/video")
    ResponseEntity<VideoDTO> addVideo(@RequestBody VideoCreationDTO video, @RequestHeader("name") String name, @RequestHeader("password") String password, UriComponentsBuilder builder) throws ExceptionIncoherentUserInformations, ExceptionVideoInvalidInformations {
        UUID userId;
        userId = facadeUtilisateur.validationUtilisateur(name, password);

        Video vid = facadeVideo.ajouterVideo(video.getTitre(), video.getDescription(), video.getUrl(), userId);

        VideoDTO dto = factory.videoDTOFactory(vid);

        String path = builder.path("/api/video/{id}").buildAndExpand(vid.getUuid()).toUriString();
        return ResponseEntity
                .status(201)
                .header("Location", path)
                .body(dto);
    }

    /**
     * retourne toutes les vidéos existantes
     * @return une collection des vidéos existantes
     */
    @GetMapping("/video")
    ResponseEntity<Collection<VideoDTO>> getAllVideos() {
        Collection<Video> videos = facadeVideo.getAllVideos();
        Collection<VideoDTO> videosDto = videos.stream().map(video -> factory.videoDTOFactory(video)).toList();
        return ResponseEntity.ok().body(videosDto);
    }

    /**
     * récupère la vidéo avec l'id donné
     * @param id l'id de la vidéo
     * @return la vidéo
     * @throws ExceptionVideoNotFound
     */
    @GetMapping("/video/{id}")
    ResponseEntity<VideoDTO> getVideoById(@PathVariable UUID id) throws ExceptionVideoNotFound {
        Video video;
        video = facadeVideo.getVideo(id);

        VideoDTO videoDto = new VideoDTO(video.getTitre(), video.getDecription(), video.getUrl(), video.getUuid().toString(), video.getUserUUID().toString());
        return ResponseEntity.ok().body(videoDto);
    }

    /**
     * récupérer toutes les vidéos d'un user
     * @param name le login de l'utilisateur
     * @param password le mdp de l'utilisateur
     * @return la collection des videos de l'utilisateur
     * @throws ExceptionIncoherentUserInformations
     */
    @GetMapping("/user/video")
    ResponseEntity<Collection<VideoDTO>> getAllVideosFromUser(@RequestHeader("name") String name, @RequestHeader("password") String password) throws ExceptionIncoherentUserInformations {
        UUID userId;
        userId = facadeUtilisateur.validationUtilisateur(name, password);

        Collection<Video> videos = facadeVideo.getVideoFromUser(userId);
        List<VideoDTO> videosDto = videos.stream().map(video -> factory.videoDTOFactory(video)).toList();
        return ResponseEntity.ok().body(videosDto);
    }

    /**
     * Récupère et retourne les informations liés à l'utilisateur
     * @param name le login de l'utilisateur
     * @param password le mdp de l'utilisateur
     * @return Les informations de l'utilisateur
     * @throws ExceptionIncoherentUserInformations
     * @throws ExceptionUserNotFound
     */
    @GetMapping("/user")
    ResponseEntity<UserDTO> getOneself(@RequestHeader("name") String name, @RequestHeader("password") String password) throws ExceptionIncoherentUserInformations, ExceptionUserNotFound {
        UUID userId;
        userId = facadeUtilisateur.validationUtilisateur(name, password);

        User user;
        user = facadeUtilisateur.getUserById(userId);

        Collection<Video> videos = facadeVideo.getVideoFromUser(userId);

        UserDTO userDto = new UserDTO(user.getNom(), user.getPlaylists(), videos, userId);

        return ResponseEntity.ok().body(userDto);
    }

    /**
     * Retourne toutes les playlist de l'utilisateur connecté
     * @param name le nom de l'utilisateur
     * @param password le pseudo de l'utilisateur
     * @return les playlists de l'utilisateur
     * @throws ExceptionIncoherentUserInformations
     * @throws ExceptionUserNotFound
     */
    @GetMapping("/user/playlist")
    ResponseEntity<UserPlaylistsDTO> getPlaylistByUser(@RequestHeader("name") String name, @RequestHeader("password") String password) throws ExceptionIncoherentUserInformations, ExceptionUserNotFound {
        UUID userId;
        userId = facadeUtilisateur.validationUtilisateur(name, password);


        Collection<Playlist> playlists;
        playlists = facadeUtilisateur.getPlaylistsByUser(userId);
        UserPlaylistsDTO userPlaylistsDto = new UserPlaylistsDTO(playlists);
        return ResponseEntity.ok().body(userPlaylistsDto);
    }

    /**
     * Crée une playlist
     * @param name le nom de l'utilisateur
     * @param password le pseudo de l'utilisateur
     * @param playlistInfos les informations attendues pour créer la playlist
     * @return La localisation de la playlist et la playlist
     * @throws ExceptionIncoherentUserInformations
     * @throws ExceptionUserNotFound
     */
    @PostMapping("/user/playlist")
    ResponseEntity<PlaylistDTO> createPlaylist(@RequestHeader("name") String name, @RequestHeader("password") String password, @RequestBody PlaylistCreationDTO playlistInfos, UriComponentsBuilder builder) throws ExceptionIncoherentUserInformations, ExceptionUserNotFound {
        UUID userId;
        userId = facadeUtilisateur.validationUtilisateur(name, password);

        Playlist playlist;

        playlist = facadeUtilisateur.createPlaylistForUser(userId, playlistInfos.getName());

        PlaylistDTO playlistDto = factory.playlistDTOFactory(playlist);

        String path = builder.path("/api/user/playlist/{idPlaylist}").buildAndExpand(playlistDto.getUuid()).toUriString();

        return ResponseEntity.status(HttpStatus.CREATED).header("Location", path).body(playlistDto);
    }

    /**
     * Ajoute une vidéo à la playlist donnée dans l'url
     * @param playlistId l'id de la playlist
     * @param name le nom du user
     * @param password le mdp du user
     * @param videoDto les informations utiles à l'ajout de la video dans la playlist
     * @return nothing
     * @throws ExceptionIncoherentUserInformations
     * @throws ExceptionVideoNotFound
     * @throws ExceptionUserNotFound
     * @throws ExceptionPlaylistNotFound
     */
    @PostMapping("/user/playlist/{playlistId}/video")
    ResponseEntity<Void> addVideoToPlaylist(@PathVariable UUID playlistId, @RequestHeader("name") String name, @RequestHeader("password") String password, @RequestBody PlaylistVideoAdditionDTO videoDto) throws ExceptionIncoherentUserInformations, ExceptionVideoNotFound, ExceptionUserNotFound, ExceptionPlaylistNotFound {
        UUID userId;
        userId = facadeUtilisateur.validationUtilisateur(name, password);

        Video video;
        video = facadeVideo.getVideo(videoDto.getVideoId());

        facadeUtilisateur.addVideoToPlaylist(userId, playlistId, video);

        return ResponseEntity.ok().build();
    }

    /**
     * Supprime une video de la playlist de l'utilisateur
     * @param name le login de l'utilisateur
     * @param password le mdp de l'utilisateur
     * @param idPlaylist l'id de la playlist de l'utilisateur
     * @param idVideo l'id de la video dans la playlist à supprimer
     * @return rien
     * @throws ExceptionIncoherentUserInformations
     * @throws ExceptionVideoNotFound
     * @throws ExceptionUserNotFound
     * @throws ExceptionPlaylistNotFound
     */
    @DeleteMapping("/user/playlist/{idPlaylist}/video/{idVideo}")
    ResponseEntity<Void> deleteVideoFromPlaylist(@RequestHeader("name") String name, @RequestHeader("password") String password, @PathVariable UUID idPlaylist, @PathVariable UUID idVideo) throws ExceptionIncoherentUserInformations, ExceptionVideoNotFound, ExceptionUserNotFound, ExceptionPlaylistNotFound {
        UUID userId;
        userId = facadeUtilisateur.validationUtilisateur(name, password);

        facadeUtilisateur.removeVideoFromUserPlaylist(idPlaylist, userId, idVideo);
        return ResponseEntity.ok().build();
    }
}
