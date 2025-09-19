package fr.univ.orleans.info.m1.ws.tp4.security.config;

import fr.univ.orleans.info.m1.ws.tp4.modele.FacadeUtilisateurs;
import fr.univ.orleans.info.m1.ws.tp4.modele.Role;
import fr.univ.orleans.info.m1.ws.tp4.security.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Injection de dépendance via constructeur (l'injection via @Autowired sur attribut est dépréciée)
    private final FacadeUtilisateurs facadeUtilisateurs;

    public SecurityConfig(@Autowired FacadeUtilisateurs facadeUtilisateurs) {
        this.facadeUtilisateurs = facadeUtilisateurs;
    }

    /**
     * Configuration des permissions d'accès aux différentes URIs du WebService.
     */
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/utilisateurs").permitAll()
                        .requestMatchers("/api/questions/**").hasRole(Role.ENSEIGNANT.name())
                        .requestMatchers("/api/utilisateurs/**").hasRole(Role.ETUDIANT.name())
                        .anyRequest().denyAll())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    /**
     * Service de génération des détails d'authentification d'un utilisateur.
     */
    @Bean
    protected UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(facadeUtilisateurs);
    }

}

