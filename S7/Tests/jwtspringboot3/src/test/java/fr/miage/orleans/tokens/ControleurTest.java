package fr.miage.orleans.tokens;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.orleans.tokens.modele.FacadeApplication;
import fr.miage.orleans.tokens.modele.FacadeUtilisateurs;
import fr.miage.orleans.tokens.modele.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.mockito.Mockito.doReturn;
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
    void loginOk() throws Exception {
        String email = "moi@moi.moi";
        String password = "test";
        String encodePassword = "encodedTest";

        doReturn(encodePassword).when(passwordEncoder).encode(password);
        Utilisateur utilisateur = new Utilisateur(email, encodePassword);

        doReturn(utilisateur).when(facadeUtilisateurs).inscrireUtilisateur(email, password);

        mockMvc.perform(post(URI.create("/api/utilisateurs")).contentType(MediaType.APPLICATION_FORM_URLENCODED).content(objectMapper.writeValueAsString(utilisateur)))
                .andExpect(status().isCreated());
    }
}