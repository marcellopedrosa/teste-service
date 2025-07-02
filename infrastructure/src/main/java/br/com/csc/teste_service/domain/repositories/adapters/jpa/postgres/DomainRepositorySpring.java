package br.com.csc.teste_service.domain.repositories.adapters.jpa.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.csc.teste_service.domain.repositories.entities.DomainEntity;

@Repository
public interface DomainRepositorySpring extends JpaRepository<DomainEntity, String> {

    @Query("SELECT d FROM DomainEntity d WHERE d.attributeEntity.code = ?1")
    public List<DomainEntity> getAllDomainsByAttributeCode(String code);   
}
