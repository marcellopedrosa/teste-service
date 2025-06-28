package br.com.csc.admin_service.domain.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.csc.admin_service.attribute.mappers.AttributeMapper;
import br.com.csc.admin_service.domain.repositories.entities.DomainEntity;
import br.com.csc.admin_service.enuns.EnumStatus;
import br.com.csc.admin_service.models.Domain;
import jakarta.inject.Inject;

@Component
public class DomainMapper {

    @Inject
    private AttributeMapper attributeMapper;

    public Domain toDomainModel(DomainEntity entity) {
        
        if (entity == null) {
            return null;
        }
        
        Domain domain = new Domain();
        domain.setDomainId(entity.getDomainId());
        domain.setAttribute(this.attributeMapper.toAttributeModel(entity.getAttributeEntity()));
        domain.setValue(entity.getValue());
        domain.setName(entity.getName());
        domain.setObs(entity.getObs());
        domain.setStatus(EnumStatus.get(entity.getStatus()));
        return domain;
    }

    public List<Domain> toDomainModelList(List<DomainEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                       .map(this::toDomainModel)
                       .toList();
    }
}
