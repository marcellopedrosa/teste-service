package br.com.csc.admin_service.attribute.repositories.adapters.jpa.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.csc.admin_service.attribute.repositories.entities.AttributeEntity;

@Repository
public interface AttributeRepositorySpring extends JpaRepository<AttributeEntity, String> {
    AttributeEntity findByCode(String code);
}
