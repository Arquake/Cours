package fr.miage.orleans.tokens;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.orleans.tokens.controleur.dtos.LoginDTO;
import fr.miage.orleans.tokens.modele.FacadeApplication;
import fr.miage.orleans.tokens.modele.FacadeUtilisateurs;
import fr.miage.orleans.tokens.modele.Utilisateur;
import fr.miage.orleans.tokens.modele.exceptions.UtilisateurInexistantException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc

public class TestTokensApplication {

    @Autowired
    MockMvc mvc;


    @MockBean
    FacadeUtilisateurs facadeUtilisateurs;



    @MockBean
    PasswordEncoder passwordEncoder;


    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void testLogin() throws Exception {

        String email = "test@univ-orleans.fr";
        String passwordClair = "passwordtestclair";
        String passwordEncode = "passwordtestencode";
        int id = 12;

        LoginDTO loginDTO = new LoginDTO(email, passwordClair);
        Utilisateur utilisateur = mock(Utilisateur.class);
        doReturn(utilisateur).when(facadeUtilisateurs).getUtilisateurByEmail(loginDTO.email());
        doReturn(passwordEncode).when(utilisateur).getEncodedPassword();
        doReturn(email).when(utilisateur).getEmail();
        doReturn(id).when(utilisateur).getIdUtilisateur();

        doReturn(true).when(passwordEncoder).matches(passwordClair, passwordEncode);

        mvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Authorization"));


    }



    @Test
    public void testLogin2() throws Exception {

        String email = "test@univ-orleans.fr";
        String passwordClair = "passwordtestclair";
        String passwordEncode = "passwordtestencode";
        int id = 12;

        LoginDTO loginDTO = new LoginDTO(email, passwordClair);
        Utilisateur utilisateur = mock(Utilisateur.class);
        doThrow(UtilisateurInexistantException.class).when(facadeUtilisateurs).getUtilisateurByEmail(loginDTO.email());

        mvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isUnauthorized());
      }

    @Test
    public void testLogin3() throws Exception {

        String email = "test@univ-orleans.fr";
        String passwordClair = "passwordtestclair";
        String passwordEncode = "passwordtestencode";
        int id = 12;

        LoginDTO loginDTO = new LoginDTO(email, passwordClair);
        Utilisateur utilisateur = mock(Utilisateur.class);
        doReturn(utilisateur).when(facadeUtilisateurs).getUtilisateurByEmail(loginDTO.email());
        doReturn(passwordEncode).when(utilisateur).getEncodedPassword();
        doReturn(email).when(utilisateur).getEmail();
        doReturn(id).when(utilisateur).getIdUtilisateur();

        doReturn(false).when(passwordEncoder).matches(passwordClair, passwordEncode);

        mvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isUnauthorized());


    }




}
