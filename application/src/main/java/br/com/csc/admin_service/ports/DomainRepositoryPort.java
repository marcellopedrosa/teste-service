package br.com.csc.admin_service.ports;

import java.util.List;
import br.com.csc.admin_service.models.Domain;

public interface DomainRepositoryPort {
    public List<Domain> getAllStatus();
}
