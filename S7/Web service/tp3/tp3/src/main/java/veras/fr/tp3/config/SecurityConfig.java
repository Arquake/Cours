package veras.fr.tp3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import veras.fr.tp3.model.CustomUserDetailsService;
import veras.fr.tp3.model.FacadeUtilisateurs;

@Configuration
public class SecurityConfig {
    private final FacadeUtilisateurs facadeUtilisateurs;

    public SecurityConfig(FacadeUtilisateurs facadeUtilisateurs) {
        this.facadeUtilisateurs = facadeUtilisateurs;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/utilisateurs").permitAll()
                        .requestMatchers("/utilisateurs/*/question").authenticated()
                        .anyRequest().denyAll()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(facadeUtilisateurs);
    }
}