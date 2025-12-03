package fr.miage.orleans.tokens.controleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.orleans.tokens.controleur.dtos.LoginDTO;
import fr.miage.orleans.tokens.modele.FacadeApplication;
import fr.miage.orleans.tokens.modele.FacadeUtilisateurs;
import fr.miage.orleans.tokens.modele.Utilisateur;
import fr.miage.orleans.tokens.modele.exceptions.LoginDejaUtiliseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControleurTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

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

        doReturn(encodePassword).when(passwordEncoder).encode(password);
        Utilisateur utilisateur = new Utilisateur(email, encodePassword);

        doReturn(utilisateur).when(facadeUtilisateurs).getUtilisateurByEmail(email);

         /*MvcResult res = mockMvc.perform(post(URI.create("/api/utilisateurs"))
                        .param("email", email)
                        .param("password", password)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

         String resBody = res.getResponse().getContentAsString();
         Utilisateur user = objectMapper.readValue(resBody, Utilisateur.class);*/

        mockMvc.perform(post(URI.create("/api/utilisateurs"))
                .param("email", email)
                .param("password", password)
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(post(URI.create("/api/login"))
                .content(objectMapper.writeValueAsString(loginDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}