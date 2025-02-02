package com.snglr.precios.conf;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/test/**", "/actuator/**", "/v3/api-docs/**",
				"/swagger-ui/**", "/swagger/**", "/h2-console/**").permitAll().anyRequest().authenticated())
				.oauth2ResourceServer(
						oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new JwtAuthenticationConverter())))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(AbstractHttpConfigurer::disable)
				.headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin));
		return http.build();
	}
	
	/* Fake JWT Decoder for demonstration purpose only */
	@Bean
    public JwtDecoder jwtDecoder() {
        return token -> {
            // Simula un JWT decodificado sin validar la firma
            Map<String, Object> headers = new HashMap<>();
            headers.put("alg", "none");

            Map<String, Object> claims = new HashMap<>();
            claims.put("sub", "fake-user");
            claims.put("iss", "fake-issuer");
            claims.put("iat", Instant.now().getEpochSecond());
            claims.put("exp", Instant.now().plusSeconds(3600).getEpochSecond()); // Expira en 1 hora
            claims.put("roles", Collections.singletonList("ROLE_USER"));

            return new Jwt(token, Instant.now(), Instant.now().plusSeconds(3600), headers, claims);
        };
    }
}
