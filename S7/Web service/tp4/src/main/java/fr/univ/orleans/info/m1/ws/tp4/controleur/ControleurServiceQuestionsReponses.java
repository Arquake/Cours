package fr.univ.orleans.info.m1.ws.tp4.controleur;

import fr.univ.orleans.info.m1.ws.tp4.modele.FacadeApplication;
import fr.univ.orleans.info.m1.ws.tp4.modele.FacadeUtilisateurs;
import fr.univ.orleans.info.m1.ws.tp4.modele.Question;
import fr.univ.orleans.info.m1.ws.tp4.modele.Utilisateur;
import fr.univ.orleans.info.m1.ws.tp4.modele.exceptions.AccessIllegalAUneQuestionException;
import fr.univ.orleans.info.m1.ws.tp4.modele.exceptions.LoginDejaUtiliseException;
import fr.univ.orleans.info.m1.ws.tp4.modele.exceptions.QuestionInexistanteException;
import fr.univ.orleans.info.m1.ws.tp4.modele.exceptions.UtilisateurInexistantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ControleurServiceQuestionsReponses {

    private static final String FILTRE_SANS_REPONSE = "sansReponse";
    private static final String FILTRE_AVEC_REPONSES = "avecReponses";

    // Injection des dépendances via le constructeur (l'injection via @Autowired sur attribut est dépréciée)

    private final FacadeUtilisateurs facadeUtilisateurs;

    private final FacadeApplication facadeApplication;

    private final PasswordEncoder passwordEncoder;

    public ControleurServiceQuestionsReponses(@Autowired FacadeUtilisateurs facadeUtilisateurs,
                                              @Autowired FacadeApplication facadeApplication,
                                              @Autowired PasswordEncoder passwordEncoder) {
        this.facadeUtilisateurs = facadeUtilisateurs;
        this.facadeApplication = facadeApplication;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/utilisateurs")
    public ResponseEntity<Utilisateur> inscrire(@RequestParam String email,
                                                @RequestParam String password) {
        Utilisateur utilisateur;
        try {
            utilisateur = facadeUtilisateurs.inscrireUtilisateur(email, passwordEncoder.encode(password));
        } catch (LoginDejaUtiliseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idUtilisateur}")
                .buildAndExpand(utilisateur.getIdUtilisateur())
                .toUri();
        return ResponseEntity.created(location).body(utilisateur);
    }

    @PostMapping("/utilisateurs/{idUtilisateur}/questions")
    public ResponseEntity<Question> ajouterQuestion(@PathVariable int idUtilisateur,
                                                    @RequestBody String libelleQuestion,
                                                    Principal principal)
            throws UtilisateurInexistantException {
        Utilisateur utilisateur = facadeUtilisateurs.getUtilisateurByEmail(principal.getName());
        if (idUtilisateur == utilisateur.getIdUtilisateur()) {
            Question question = facadeApplication.ajouterUneQuestion(idUtilisateur, libelleQuestion);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{idQuestion}")
                    .buildAndExpand(question.getIdQuestion())
                    .toUri();
            return ResponseEntity.created(location).body(question);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/utilisateurs/{idUtilisateur}/questions")
    public ResponseEntity<Collection<Question>> getSomeQuestionsByUtilisateur(@PathVariable int idUtilisateur,
                                                                              @RequestParam Optional<String> filtre,
                                                                              Authentication authentication) {
        try {
            int id = facadeUtilisateurs.getUtilisateurIntId(authentication.getName());

            if (idUtilisateur == id) {
                String f = filtre.orElse("sansFiltre");
                return switch (f) {
                    case FILTRE_SANS_REPONSE ->
                            ResponseEntity.ok(facadeApplication.getQuestionsSansReponsesByUser(idUtilisateur));
                    case FILTRE_AVEC_REPONSES ->
                            ResponseEntity.ok(facadeApplication.getQuestionsAvecReponsesByUser(idUtilisateur));
                    default -> ResponseEntity.ok(facadeApplication.getToutesLesQuestionsByUser(idUtilisateur));
                };
            } else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (UtilisateurInexistantException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/utilisateurs/{idUtilisateur}/questions/{idQuestion}")
    public ResponseEntity<Question> getQuestionByUtilisateur(@PathVariable int idUtilisateur,
                                                             @PathVariable String idQuestion,
                                                             Authentication authentication)
            throws UtilisateurInexistantException, AccessIllegalAUneQuestionException, QuestionInexistanteException {
        int id = facadeUtilisateurs.getUtilisateurIntId(authentication.getName());
        if (idUtilisateur == id) {
            Question question = facadeApplication.getQuestionByIdPourUnUtilisateur(idUtilisateur, idQuestion);
            return ResponseEntity.ok(question);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping("/questions/{idQuestion}")
    public ResponseEntity<String> repondreQuestion(@PathVariable String idQuestion,
                                                   @RequestBody String reponse)
            throws QuestionInexistanteException {
        this.facadeApplication.repondreAUneQuestion(idQuestion, reponse);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/questions")
    public ResponseEntity<Collection<Question>> getQuestion(@RequestParam Optional<String> filtre) {
        if (filtre.isPresent() && FILTRE_SANS_REPONSE.equals(filtre.get())) {
            return ResponseEntity.ok(facadeApplication.getQuestionsSansReponses());
        } else {
            return ResponseEntity.ok(facadeApplication.getToutesLesQuestions());
        }
    }

}
