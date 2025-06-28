package br.com.csc.admin_service.domain.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.csc.admin_service.ports.DomainRepositoryPort;
import jakarta.inject.Inject;


@RestController
@RequestMapping("/domain")
public class DomainController {

    @Inject 
    private DomainRepositoryPort domainRepositoryPort;
    
    @GetMapping("/v1/status/list")
    public ResponseEntity<?> getAllStatus() {
        try {
            return ResponseEntity.ok(this.domainRepositoryPort.getAllStatus());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                                 .body(e.getMessage());
        }
    }
    
}