package br.com.csc.teste_service.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestCustomizers;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_ROUTE = { "/teste/**", 
                                                   "/actuator/health", 
                                                   "/actuator/info",
                                                   "/actuator/prometheus",
                                                   "/actuator/metrics",
                                                   "/actuator/httptrace",
                                                   "/actuator/refresh" };

    private static final String[] DISABLE_GATEWAY_ROUTES = {"/actuator/gateway/**"};

    // ############# v2 #############
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http,
            ServerOAuth2AuthorizationRequestResolver resolver,
            DynamicReactiveJwtDecoderResolver jwtDecoderResolver) {

        http
                .cors(Customizer.withDefaults())
                .headers(headers -> headers
                        .contentTypeOptions(ServerHttpSecurity.HeaderSpec.ContentTypeOptionsSpec::disable)
                        .frameOptions(ServerHttpSecurity.HeaderSpec.FrameOptionsSpec::disable))
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(PUBLIC_ROUTE).permitAll()
                        .pathMatchers("/**").hasAuthority("SCOPE_admin_gateway")
                        .pathMatchers(HttpMethod.DELETE, DISABLE_GATEWAY_ROUTES).denyAll()
                        .pathMatchers(HttpMethod.POST, DISABLE_GATEWAY_ROUTES).denyAll()
                        .anyExchange().denyAll())
                .oauth2Login(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtDecoder(jwtDecoderResolver::decodeV1)) 
                );

        http.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }

    // ############# v1 #############
    // @Bean
    // public SecurityWebFilterChain springSecurityFilterChain(
    //         ServerHttpSecurity http, ServerOAuth2AuthorizationRequestResolver resolver) {
    //     http.cors(Customizer.withDefaults())
    //         .headers(headers -> headers
    //                     .contentTypeOptions(ServerHttpSecurity.HeaderSpec.ContentTypeOptionsSpec::disable)
    //                     .frameOptions(ServerHttpSecurity.HeaderSpec.FrameOptionsSpec::disable))
    //             .authorizeExchange(exchanges -> exchanges
    //                     .pathMatchers(PUBLIC_ROUTE).permitAll()
    //                     .pathMatchers("/**").hasAuthority("SCOPE_admin_gateway")
    //                     .pathMatchers(HttpMethod.DELETE, DISABLE_GATEWAY_ROUTES).denyAll()
    //                     .pathMatchers(HttpMethod.POST, DISABLE_GATEWAY_ROUTES).denyAll()
    //                     .anyExchange().denyAll())
    //             .oauth2Login(withDefaults())
    //             .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
    //     http.oauth2Login(withDefaults());
    //     http.csrf(ServerHttpSecurity.CsrfSpec::disable);
    //     return http.build();
    //}

    @Bean
    public ServerOAuth2AuthorizationRequestResolver pkceResolver(ReactiveClientRegistrationRepository repo) {
        var resolver = new DefaultServerOAuth2AuthorizationRequestResolver(repo);
        resolver.setAuthorizationRequestCustomizer(OAuth2AuthorizationRequestCustomizers.withPkce());
        return resolver;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // Permite qualquer origem
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*")); // Permite qualquer header
        //config.setAllowCredentials(false); // NÃO permitir cookies/autenticação cross-origin

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
