package fr.miage.orleans.tokens.controleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.orleans.tokens.modele.FacadeApplication;
import fr.miage.orleans.tokens.modele.FacadeUtilisateurs;
import fr.miage.orleans.tokens.modele.Utilisateur;
import fr.miage.orleans.tokens.modele.exceptions.LoginDejaUtiliseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class ControleurTest {

    public ControleurTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @MockBean
    FacadeUtilisateurs facadeUtilisateurs;

    @MockBean
    FacadeApplication facadeApplication;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Test
    void loginOk() throws LoginDejaUtiliseException {
        String email = "moi@moi.moi";
        String password = "test";
        String encodePassword = "encodedTest";

        doReturn(encodePassword).when(passwordEncoder).encode(password);
        Utilisateur utilisateur = new Utilisateur(email, encodePassword);

        doReturn(utilisateur).when(facadeUtilisateurs).inscrireUtilisateur(email, password);

        mockMvc.perform(post(URI.create("/api/utilisateurs")).contentType(MediaType.APPLICATION_JSON))
    }
}