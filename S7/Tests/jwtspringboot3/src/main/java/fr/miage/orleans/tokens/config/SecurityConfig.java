package fr.miage.orleans.tokens.config;


import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import fr.miage.orleans.tokens.modele.Role;
import fr.miage.orleans.tokens.modele.Utilisateur;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/utilisateurs").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                        .requestMatchers("/api/questions/**").hasRole(Role.ENSEIGNANT.name())
                        .requestMatchers("/api/utilisateurs/**").hasRole(Role.ETUDIANT.name())
                        .anyRequest().denyAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()));
        return http.build();
    }


    @Bean
    public JwtEncoder jwtEncoder(JWK jwk) {

        // Créer un objet JWKSet avec la clé secrète
        JWKSet jwkSet = new JWKSet(jwk);

        // Créer un JWKSource avec la JWKSet
        JWKSource<SecurityContext> jwkSource = (jwkSelector, context) -> jwkSet.getKeys();

        NimbusJwtEncoder nimbusJwtEncoder = new NimbusJwtEncoder(jwkSource);

        return nimbusJwtEncoder;
    }

    @Bean
    public JwtDecoder jwtDecoder(JWK jwk) {
        return NimbusJwtDecoder.withSecretKey(jwk.toOctetSequenceKey().toSecretKey()).build();
    }


    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        String idForEncode = "bcrypt";;
        PasswordEncoder defaultEncoder = new BCryptPasswordEncoder();
        Map<String, PasswordEncoder> encoders = Map.of(
                idForEncode, defaultEncoder,
                "noop", NoOpPasswordEncoder.getInstance(),
                "scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v4_1(),
                "sha256", new StandardPasswordEncoder()
        );

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }


    private static String[] getRoles(String email) {
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

    @Bean
    Function<Utilisateur,String> genereTokenFunction(JWK jwk) {

        return personne -> {

            Instant now = Instant.now();
            long expiry = 36000L;

            String scope = Arrays.stream(getRoles(personne.getEmail())).map(x -> x.toString()).collect(Collectors.joining(" "));
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiry))
                    .subject(personne.getEmail())
                    .claim("scope", scope)
                    .claim("idUtilisateur",String.valueOf(personne.getIdUtilisateur()))
                    .build();


            JWSHeader.Builder b = new JWSHeader.Builder(JWSAlgorithm.ES256);

            JwsHeader myJwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();


            return jwtEncoder(jwk).encode(JwtEncoderParameters.from(myJwsHeader, claims)).getTokenValue();
        };
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }



}
