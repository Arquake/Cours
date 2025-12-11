package com.example.tp4.security.config;

import com.example.tp4.modele.FacadeUser;
import com.example.tp4.modele.Role;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Configuration
public class SecurityConfig {

    // Injection de dépendance via constructeur (l'injection via @Autowired sur attribut est dépréciée)

    /**
     * Configuration des permissions d'accès aux différentes URIs du WebService.
     */
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/utilisateurs").permitAll()
                        .requestMatchers("/api/questions/**").hasRole(Role.ENSEIGNANT.name())
                        .requestMatchers("/api/utilisateurs/**").hasRole(Role.ETUDIANT.name())
                        .anyRequest().denyAll())
                .oauth2ResourceServer(oauth->oauth.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


    @Bean
    UserDetailsManager userDetailsManager() {
        return new FacadeUser();
    }


    @Bean
    public JwtEncoder jwtEncoder(JWK jwk) {

        // Créer un objet JWKSet avec la clé secrète
        JWKSet jwkSet = new JWKSet(jwk);

        // Créer un JWKSource avec la JWKSet
        JWKSource<SecurityContext> jwkSource = (jwkSelector, context) -> jwkSet.getKeys();

        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWK jwk) {
        return NimbusJwtDecoder.withSecretKey(jwk.toOctetSequenceKey().toSecretKey()).build();
    }


    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        String idForEncode = "bcrypt";
        PasswordEncoder defaultEncoder = new BCryptPasswordEncoder();
        Map<String, PasswordEncoder> encoders = Map.of(
                idForEncode, defaultEncoder,
                "noop", NoOpPasswordEncoder.getInstance(),
                "scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v4_1(),
                "sha256", new StandardPasswordEncoder()
        );

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }


    @Bean
    Function<UserDetails,String> genereTokenFunction(JWK jwk) {

        return personne -> {

            Instant now = Instant.now();
            long expiry = 36000L;

            List<String> scope = personne.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(Objects::nonNull)
                    .map(auth -> auth.replace("ROLE_", ""))
                    .toList();
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiry))
                    .subject(personne.getUsername())
                    .claim("scope", scope)
                    .build();

            JwsHeader myJwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();


            return jwtEncoder(jwk).encode(JwtEncoderParameters.from(myJwsHeader, claims)).getTokenValue();
        };
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("scope");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}

