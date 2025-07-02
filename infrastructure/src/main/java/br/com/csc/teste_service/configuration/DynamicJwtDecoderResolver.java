package br.com.csc.admin_service.configuration;

import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Resolver dinâmico de JwtDecoder com cache por issuer.
 * Suporta dois modos:
 * - decodeV1: resolve sem validação explícita de issuer
 * - decodeV2: valida o issuer contra lista de permitidos
 */
@Component
public class DynamicJwtDecoderResolver {

    private final Map<String, JwtDecoder> cache = new ConcurrentHashMap<>();
    private final Set<String> allowedIssuers;

    public DynamicJwtDecoderResolver(
            @Value("${SPI_URI_SERVER}") String issuer1
    // ,@Value("${SPI_URI_SERVER_QA}") String issuer2
    ) {
        this.allowedIssuers = Set.of(issuer1);
        // this.allowedIssuers = Set.of(issuer1, issuer2);
    }

    /**
     * decodeV2: Valida o issuer com base nas variáveis de ambiente (seguro)
     * 
     * @param token JWT
     * @return Jwt decodificado e validado
     * @throws JwtException se inválido ou issuer não autorizado
     */
    public Jwt decodeV2(String token) {
        try {
            SignedJWT parsed = SignedJWT.parse(token);
            String issuer = parsed.getJWTClaimsSet().getIssuer();

            if (issuer == null || issuer.isBlank()) {
                throw new JwtException("Issuer (iss) não encontrado no token");
            }

            if (!allowedIssuers.contains(issuer)) {
                throw new JwtException("Issuer não autorizado: " + issuer);
            }

            JwtDecoder decoder = cache.computeIfAbsent(issuer, iss -> {
                String jwksUri = iss + "/protocol/openid-connect/certs";
                NimbusJwtDecoder nimbusDecoder = NimbusJwtDecoder.withJwkSetUri(jwksUri).build();
                OAuth2TokenValidator<Jwt> validator = JwtValidators.createDefaultWithIssuer(iss);
                nimbusDecoder.setJwtValidator(validator);
                return nimbusDecoder;
            });

            return decoder.decode(token);

        } catch (ParseException e) {
            throw new JwtException("Token JWT malformado", e);
        }
    }

    /**
     * decodeV1: Apenas resolve o decoder com base no issuer do token, sem validação
     * de lista de issuers
     * Ideal quando se confia na assinatura e o issuer é confiável via JWKS
     * 
     * @param token JWT
     * @return Jwt decodificado
     * @throws JwtException se malformado ou issuer ausente
     */
    public Jwt decodeV1(String token) {
        try {
            SignedJWT parsed = SignedJWT.parse(token);
            String issuer = parsed.getJWTClaimsSet().getIssuer();

            if (issuer == null || issuer.isBlank()) {
                throw new JwtException("Issuer (iss) ausente no token");
            }

            JwtDecoder decoder = cache.computeIfAbsent(issuer, iss -> {
                String jwksUri = iss + "/protocol/openid-connect/certs";
                return NimbusJwtDecoder.withJwkSetUri(jwksUri).build();
            });

            return decoder.decode(token);

        } catch (ParseException e) {
            throw new JwtException("Token JWT malformado", e);
        }
    }
}