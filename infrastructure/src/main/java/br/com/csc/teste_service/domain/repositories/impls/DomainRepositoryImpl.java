package br.com.csc.teste_service.domain.repositories.impls;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.csc.teste_service.domain.mappers.DomainMapper;
import br.com.csc.teste_service.domain.repositories.adapters.jpa.postgres.DomainRepositorySpring;
import br.com.csc.teste_service.domain.repositories.entities.DomainEntity;
import br.com.csc.teste_service.models.Domain;
import br.com.csc.teste_service.ports.DomainRepositoryPort;
import jakarta.inject.Inject;

@Component
public class DomainRepositoryImpl implements DomainRepositoryPort {

    @Inject
    private DomainRepositorySpring domainRepositorySpring;

    @Inject
    private DomainMapper domainMapper;

    @Override
    public List<Domain> getAllStatus() {
        List<DomainEntity> list = this.domainRepositorySpring.getAllDomainsByAttributeCode("COD_STATUS");
        return this.domainMapper.toDomainModelList(list);
    }
}
