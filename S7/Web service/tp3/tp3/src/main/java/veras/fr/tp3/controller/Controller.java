package veras.fr.tp3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import veras.fr.tp3.Dtos.QuestionDTO;
import veras.fr.tp3.Dtos.QuestionDTOOut;
import veras.fr.tp3.Dtos.UtilisateurCreationDTO;
import veras.fr.tp3.Dtos.UtilisateurDTO;
import veras.fr.tp3.exceptions.AccesIllegalAUneQuestionException;
import veras.fr.tp3.exceptions.LoginDejaUtiliseException;
import veras.fr.tp3.exceptions.QuestionInexistanteException;
import veras.fr.tp3.exceptions.UtilisateurInexistantException;
import veras.fr.tp3.factory.QuestionFactory;
import veras.fr.tp3.factory.UtilisateurFactory;
import veras.fr.tp3.model.FacadeApplication;
import veras.fr.tp3.model.FacadeUtilisateurs;
import veras.fr.tp3.model.Question;
import veras.fr.tp3.model.Utilisateur;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping("/utilisateur")
public class Controller {

    FacadeUtilisateurs facadeUtilisateurs;
    UtilisateurFactory utilisateurFactory;
    FacadeApplication facadeApplication;

    public Controller(FacadeUtilisateurs facadeUtilisateurs, UtilisateurFactory utilisateurFactory, FacadeApplication facadeApplication) {
        this.facadeUtilisateurs = facadeUtilisateurs;
        this.utilisateurFactory = utilisateurFactory;
        this.facadeApplication = facadeApplication;
    }

    @PostMapping
    public ResponseEntity<UtilisateurDTO> register(@RequestBody UtilisateurCreationDTO utilisateurCreationDTO) throws LoginDejaUtiliseException {
        int utilisateur = facadeUtilisateurs.inscrireUtilisateur(utilisateurCreationDTO.login(), utilisateurCreationDTO.password());

        try {
            return ResponseEntity.created(new URI("/utilisateur/"+utilisateur)).body(UtilisateurFactory.createUtilisateurDtoWithUtilisateur(facadeUtilisateurs.getUtilisateurByLogin(utilisateurCreationDTO.login())));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/{idUtilisateur}/question")
    public ResponseEntity<QuestionDTOOut> poserQuestion(@PathVariable("idUtilisateur") String idUser, @RequestBody QuestionDTO questionDTO) throws QuestionInexistanteException, AccesIllegalAUneQuestionException, UtilisateurInexistantException, URISyntaxException {
        if (!facadeUtilisateurs.isEtudiant(idUser)) {
            throw new AccesIllegalAUneQuestionException();
        }
        String questionId = facadeApplication.ajouterUneQuestion(facadeUtilisateurs.getUtilisateurIntId(idUser), questionDTO.question());
        return ResponseEntity.created(new URI("/utilisateur/"+idUser+"/question/"+questionId)).body(QuestionFactory.questionToQuestionOutDTO(facadeApplication.getQuestionByIdPourUnUtilisateur(facadeUtilisateurs.getUtilisateurIntId(idUser), questionId)));
    }

    @GetMapping("/{login}/question")
    public ResponseEntity<Collection<QuestionDTOOut>> test(@PathVariable("login") String login) throws UtilisateurInexistantException {
        Utilisateur user = facadeUtilisateurs.getUtilisateurByLogin(login);
        Collection<Question> questions = facadeApplication.getToutesLesQuestionsByUser(user.getIdUtilisateur());
        return ResponseEntity.ok().body(QuestionFactory.questionDTOOutCollection(questions));
    }
}
