package br.com.csc.apsystem_service.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Provedor de token jwt que chega nos requests
 * Evitar Token threading (não passar o token via parametro camada por camada para ser usado)
 * 
 * Como usar: declare o token numa classe com @Component e acesse o token
 * @see 
 *      @Inject
 *      private TokenProvider tokenProvide
 * @see tokenProvider.getBearerToken()
 * 
 * @author Marcello Pedrosa
 * @version 1.0.0
 */
@Component
public class TokenProvider {

    /**
     * Identificar qual microserviço (Client oauth2) não está recebendo o token no cabeçalho
     */
    @Value("${spring.application.name}")
    private String springKeycloakClientId;

    public String getBearerToken() throws Exception {
       
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String authHeader = request.getHeader("Authorization");
    
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                return Optional.of(authHeader.substring(7))
                               .orElseThrow(() -> new Exception("["+springKeycloakClientId+"] Token jwt não encontrado no Bearer"));
            }
        }
        
        throw new Exception("["+springKeycloakClientId+"] Token jwt não encontrado no request");
    }
}
