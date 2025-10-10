package veras.fr.tp3.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import veras.fr.tp3.Dtos.QuestionDTO;
import veras.fr.tp3.Dtos.UtilisateurConnexionDto;
import veras.fr.tp3.Dtos.UtilisateurCreationDTO;
import veras.fr.tp3.Dtos.UtilisateurDTO;
import veras.fr.tp3.exceptions.UtilisateurInexistantException;
import veras.fr.tp3.factory.UtilisateurFactory;
import veras.fr.tp3.model.FacadeUtilisateurs;
import veras.fr.tp3.model.Utilisateur;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class Controller {

    FacadeUtilisateurs facadeUtilisateurs;
    UtilisateurFactory utilisateurFactory;

    public Controller(FacadeUtilisateurs facadeUtilisateurs, UtilisateurFactory utilisateurFactory) {
        this.facadeUtilisateurs = facadeUtilisateurs;
        this.utilisateurFactory = utilisateurFactory;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            facadeUtilisateurs.inscrireUtilisateur("yohan.boichut@univ-orleans.fr",
                    "monMotDePasse");
            facadeUtilisateurs.inscrireUtilisateur("gerard.menvussaa@etu.univ-orleans.fr",
                    "sonMotDePasse");
        };
    }

    @RequestMapping("/login")
    @PostMapping
    public ResponseEntity<UtilisateurDTO> login(@RequestBody UtilisateurConnexionDto utilisateurConnexionDto) throws UtilisateurInexistantException {
         if(!facadeUtilisateurs.verifierMotDePasse(utilisateurConnexionDto.login(), utilisateurConnexionDto.password())) {
             throw new UtilisateurInexistantException();
         }

         Utilisateur currentUser = facadeUtilisateurs.getUtilisateurByLogin(utilisateurConnexionDto.login());
         return ResponseEntity.ok(utilisateurFactory.createUtilisateurDtoWithUtilisateur(currentUser));
    }

    @PostMapping("/utilisateur/{idUtilisateur}/question")
    public ResponseEntity<QuestionDTO> poserQuestion(@RequestParam("idUtilisateur") Integer idUser) {
        
    }

}
