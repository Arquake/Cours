package com.example.tp4.controleur;

import com.example.tp4.dtos.MapperDTO;
import com.example.tp4.dtos.QuestionDTO;
import com.example.tp4.dtos.UserDTO;
import com.example.tp4.modele.FacadeApplication;
import com.example.tp4.modele.Question;
import com.example.tp4.modele.Role;
import com.example.tp4.modele.exceptions.AccessIllegalAUneQuestionException;
import com.example.tp4.modele.exceptions.LoginDejaUtiliseException;
import com.example.tp4.modele.exceptions.QuestionInexistanteException;
import com.example.tp4.modele.exceptions.UtilisateurInexistantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ControleurServiceQuestionsReponses {

    private static final String FILTRE_SANS_REPONSE = "sansReponse";
    private static final String FILTRE_AVEC_REPONSES = "avecReponses";


    private final MapperDTO mapperDTO;
    private final UserDetailsManager facadeUtilisateurs;

    private final FacadeApplication facadeApplication;

    private final PasswordEncoder passwordEncoder;

    private final Function<UserDetails,String> genereTokenFunction;

    public ControleurServiceQuestionsReponses( UserDetailsManager facadeUtilisateurs,
                                               FacadeApplication facadeApplication,
                                              PasswordEncoder passwordEncoder,
                                               MapperDTO mapperDTO,
                                               Function<UserDetails,String> genereTokenFunction) {
        this.facadeUtilisateurs = facadeUtilisateurs;
        this.facadeApplication = facadeApplication;
        this.passwordEncoder = passwordEncoder;
        this.mapperDTO = mapperDTO;
        this.genereTokenFunction = genereTokenFunction;
    }


    public static String[] getRoles(String email) {
        String domain = (email.split("@"))[1];

        return switch (domain) {
            case "etu.univ-orleans.fr" -> new String[]{
                    Role.ETUDIANT.name()
            };
            case "univ-orleans.fr" -> new String[]{
                    Role.ETUDIANT.name(),
                    Role.ENSEIGNANT.name()
            };
            default -> new String[0];
        };
    }

    @PostMapping("/utilisateurs")
    public ResponseEntity<UserDTO> inscrire(@RequestParam String email,
                                            @RequestParam String password) {
        try {
            UserDetails user = User.builder()
                    .username(email)
                    .password(passwordEncoder.encode(password))
                    .roles(getRoles(email))
                    .build();

            if (facadeUtilisateurs.userExists(email))
                throw new LoginDejaUtiliseException();
            else
                facadeUtilisateurs.createUser(user);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{idUtilisateur}")
                    .buildAndExpand(user.getUsername())
                    .toUri();

            return ResponseEntity.created(location)
                    .header("Authorization","Bearer " + genereTokenFunction.apply(user))
                    .body(new UserDTO(user.getUsername(), getRoles(email)));

        } catch (LoginDejaUtiliseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @PostMapping("/utilisateurs/{idUtilisateur}/questions")
    public ResponseEntity<QuestionDTO> ajouterQuestion(@PathVariable String idUtilisateur,
                                                       @RequestBody String libelleQuestion,
                                                       Principal principal)
            throws UtilisateurInexistantException {
        if (idUtilisateur.equals(principal.getName())) {
            Question question = facadeApplication.ajouterUneQuestion(idUtilisateur, libelleQuestion);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{idQuestion}")
                    .buildAndExpand(question.getIdQuestion())
                    .toUri();
            return ResponseEntity.created(location).body(mapperDTO.convertirEnQuestionDTO(question));
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/utilisateurs/{idUtilisateur}/questions")
    public ResponseEntity<Collection<QuestionDTO>> getSomeQuestionsByUtilisateur(@PathVariable String idUtilisateur,
                                                                              @RequestParam Optional<String> filtre,
                                                                              Authentication authentication) {
        try {

            if (idUtilisateur.equals(authentication.getName())) {
                String f = filtre.orElse("sansFiltre");
                return switch (f) {
                    case FILTRE_SANS_REPONSE ->
                            ResponseEntity.ok(facadeApplication.getQuestionsSansReponsesByUser(idUtilisateur).stream().map(mapperDTO::convertirEnQuestionDTO).collect(Collectors.toList()));
                    case FILTRE_AVEC_REPONSES ->
                            ResponseEntity.ok(facadeApplication.getQuestionsAvecReponsesByUser(idUtilisateur).stream().map(mapperDTO::convertirEnQuestionDTO).collect(Collectors.toList()));
                    default -> ResponseEntity.ok(facadeApplication.getToutesLesQuestionsByUser(idUtilisateur).stream().map(mapperDTO::convertirEnQuestionDTO).collect(Collectors.toList()));
                };
            } else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (UtilisateurInexistantException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/utilisateurs/{idUtilisateur}/questions/{idQuestion}")
    public ResponseEntity<QuestionDTO> getQuestionByUtilisateur(@PathVariable String idUtilisateur,
                                                             @PathVariable String idQuestion,
                                                             Authentication authentication)
            throws UtilisateurInexistantException, AccessIllegalAUneQuestionException, QuestionInexistanteException {
        if (idUtilisateur.equals(authentication.getName())) {
            Question question = facadeApplication.getQuestionByIdPourUnUtilisateur(idUtilisateur, idQuestion);
            return ResponseEntity.ok(mapperDTO.convertirEnQuestionDTO(question));
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
    public ResponseEntity<Collection<QuestionDTO>> getQuestion(@RequestParam Optional<String> filtre) {
        if (filtre.isPresent() && FILTRE_SANS_REPONSE.equals(filtre.get())) {
            return ResponseEntity.ok(facadeApplication.getQuestionsSansReponses().stream().map(mapperDTO::convertirEnQuestionDTO).collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok(facadeApplication.getToutesLesQuestions().stream().map(mapperDTO::convertirEnQuestionDTO).collect(Collectors.toList()));
        }
    }

}
