package fr.miage.orleans.tokens.controleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.orleans.tokens.controleur.dtos.LoginDTO;
import fr.miage.orleans.tokens.modele.FacadeApplication;
import fr.miage.orleans.tokens.modele.FacadeUtilisateurs;
import fr.miage.orleans.tokens.modele.Question;
import fr.miage.orleans.tokens.modele.Utilisateur;
import fr.miage.orleans.tokens.modele.exceptions.LoginDejaUtiliseException;
import fr.miage.orleans.tokens.modele.exceptions.UtilisateurInexistantException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControleurTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Function<Utilisateur,String> genereToken;

    @MockBean
    FacadeUtilisateurs facadeUtilisateurs;

    @MockBean
    FacadeApplication facadeApplication;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Test
    void inscriptionOk() throws Exception {
        String email = "moi@moi.moi";
        String password = "test";
        String encodePassword = "encodedTest";

        doReturn(encodePassword).when(passwordEncoder).encode(password);
        Utilisateur utilisateur = new Utilisateur(email, encodePassword);

        doReturn(utilisateur).when(facadeUtilisateurs).inscrireUtilisateur(email, encodePassword);

        mockMvc.perform(post(URI.create("/api/utilisateurs"))
                .param("email", email)
                .param("password", password)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void inscriptionConflict()throws Exception {
        String email = "moi2@moi.moi";
        String password = "test2";
        String encodePassword = "encodedTest2";

        doReturn(encodePassword).when(passwordEncoder).encode(password);

        doThrow(LoginDejaUtiliseException.class).when(facadeUtilisateurs).inscrireUtilisateur(email, encodePassword);

        mockMvc.perform(post(URI.create("/api/utilisateurs"))
                        .param("email", email)
                        .param("password", password)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void loginOk()throws Exception {

        String email = "moi3@moi.moi";
        String password = "test3";
        String encodePassword = "encodedTest3";

        LoginDTO loginDTO = new LoginDTO(email, password);

        Utilisateur utilisateur = new Utilisateur(email, encodePassword);

        doReturn(utilisateur).when(facadeUtilisateurs).getUtilisateurByEmail(email);

        doReturn(true).when(passwordEncoder).matches(password, encodePassword);

        mockMvc.perform(post(URI.create("/api/login"))
                        .content(objectMapper.writeValueAsString(loginDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void loginBadPassword()throws Exception {

        String email = "moi4@moi.moi";
        String password = "test4";
        String encodePassword = "encodedTest4";

        LoginDTO loginDTO = new LoginDTO(email, password);

        Utilisateur utilisateur = new Utilisateur(email, encodePassword);

        doReturn(utilisateur).when(facadeUtilisateurs).getUtilisateurByEmail(email);

        doReturn(false).when(passwordEncoder).matches(password, encodePassword);

        mockMvc.perform(post(URI.create("/api/login"))
                        .content(objectMapper.writeValueAsString(loginDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void loginBadEmail()throws Exception {

        String email = "moi5@moi.moi";
        String password = "test5";

        LoginDTO loginDTO = new LoginDTO(email, password);

        doThrow(UtilisateurInexistantException.class).when(facadeUtilisateurs).getUtilisateurByEmail(email);

        mockMvc.perform(post(URI.create("/api/login"))
                        .content(objectMapper.writeValueAsString(loginDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    // Test vrai jwt
    @Test
    void createQuestionOkTrueJwt()throws Exception {

        String email = "moi6@univ-orleans.fr";
        String password = "test6";

        Utilisateur utilisateur = new Utilisateur(email, password);

        String libelle = "Tu préfère moi ou ton père ?";
        String location = "/api/utilisateurs/"+utilisateur.getIdUtilisateur();

        String trueToken = "Bearer " + genereToken.apply(utilisateur);

        Question q = new Question(utilisateur.getIdUtilisateur(), libelle);

        doReturn(q).when(facadeApplication).ajouterUneQuestion(utilisateur.getIdUtilisateur(), libelle);

        mockMvc.perform(post(URI.create(location + "/questions"))
                        .header("Authorization", trueToken)
                        .content(libelle)
                        .contentType(MediaType.TEXT_PLAIN)
                        )
                .andExpect(status().isCreated());
    }



    // Test faux jwt
    @Test
    void getQuestionsFalseJwt()throws Exception {
        String idUser = "1";

        Collection<Question> cq = new ArrayList<>();

        doReturn(cq).when(facadeApplication).getToutesLesQuestionsByUser(1);

        mockMvc.perform(get(URI.create("/api/utilisateurs/" + idUser + "/questions"))
                        .with(
                                jwt()
                                .authorities(List.of(new SimpleGrantedAuthority("ROLE_ETUDIANT")))
                                .jwt(jwt -> jwt.claim("idUtilisateur", idUser))
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}