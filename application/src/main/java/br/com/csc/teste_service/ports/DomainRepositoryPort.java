package br.com.csc.teste_service.ports;

import java.util.List;
import br.com.csc.teste_service.models.Domain;

public interface DomainRepositoryPort {
    public List<Domain> getAllStatus();
}
