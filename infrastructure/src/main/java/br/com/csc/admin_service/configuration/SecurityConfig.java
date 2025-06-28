package br.com.csc.admin_service.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Value("${spring.application.name}")
    private String springKeycloakClientId;

    private static final String[] PUBLIC_ENDPOINTS = {
            "/public/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
    };
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .anyRequest().hasRole("access_api"))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));

        return http.build();
    }

    /**
     * Extrai roles do token JWT no formato do Keycloak (realm_access.roles).
     */
    @SuppressWarnings("unchecked")
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter() {
            {
                setJwtGrantedAuthoritiesConverter(jwt -> {
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
                    if (resourceAccess != null && resourceAccess.containsKey(springKeycloakClientId)) {
                        Map<String, Object> microserviceClientAccess = (Map<String, Object>) resourceAccess.get(springKeycloakClientId);
                        List<String> roles = (List<String>) microserviceClientAccess.get("roles");
                        if (roles != null) {
                            for (String role : roles) {
                                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                            }
                        }
                    }
                    return authorities;
                });
            }
        };
    }
}
