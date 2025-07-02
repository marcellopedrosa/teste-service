package br.com.csc.teste_service.attribute.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.csc.teste_service.attribute.dtos.AttributeCreateRequestDto;
import br.com.csc.teste_service.attribute.dtos.AttributeUpdateRequestDto;
import br.com.csc.teste_service.attribute.dtos.AttributesResponseDto;
import br.com.csc.teste_service.attribute.repositories.entities.AttributeEntity;
import br.com.csc.teste_service.enuns.EnumStatus;
import br.com.csc.teste_service.models.Attribute;

@Component
public class AttributeMapper {
    
    public AttributeEntity toAttributeEntity(String attributeId, 
                                             String code,
                                             String name, 
                                             EnumStatus status) {
        AttributeEntity attributeEntity = new AttributeEntity();
        attributeEntity.setAttributeId(attributeId);
        attributeEntity.setCode(code);
        attributeEntity.setName(name);
        attributeEntity.setStatus(status.getCodigo());
        return attributeEntity;
    }

    public Attribute toAttributeModel(String attributeId,
                                      String code,
                                      String name,
                                      EnumStatus status) {
        Attribute attribute = new Attribute();
        attribute.setAttributeId(attributeId);
        attribute.setCode(code);
        attribute.setName(name);
        attribute.setStatus(EnumStatus.get(status.getCodigo()));
        return attribute;
    }

    public Attribute toAttributeModel(AttributeEntity attributeEntity) {
        if (attributeEntity == null) {
            return null;
        }
        Attribute attribute = new Attribute();
        attribute.setAttributeId(attributeEntity.getAttributeId());
        attribute.setCode(attributeEntity.getCode());
        attribute.setName(attributeEntity.getName());
        attribute.setStatus(EnumStatus.get(attributeEntity.getStatus()));
        return attribute;
    }

    public Attribute toAttributeUpdateModel(AttributeUpdateRequestDto attributeUpdateRequestDto) {
        if (attributeUpdateRequestDto == null) {
            return null;
        }
        Attribute attribute = new Attribute();
        attribute.setAttributeId(attributeUpdateRequestDto.getAttributeId());
        attribute.setCode(attributeUpdateRequestDto.getCode());
        attribute.setName(attributeUpdateRequestDto.getName());
        attribute.setStatus(EnumStatus.get(attributeUpdateRequestDto.getStatus()));
        return attribute;
    }

    public Attribute toAttributeNewModel(AttributeCreateRequestDto attributeCreateRequestDto) {
        if (attributeCreateRequestDto == null) {
            return null;
        }
        Attribute attribute = new Attribute();
        attribute.setAttributeId(UUID.randomUUID().toString());
        attribute.setCode(attributeCreateRequestDto.getCode());
        attribute.setName(attributeCreateRequestDto.getName());
        attribute.setStatus(EnumStatus.get(attributeCreateRequestDto.getStatus()));
        return attribute;
    }
    
    public AttributeEntity toAttributeEntity(String attributeId, 
                                             String code,
                                             String name, 
                                             String status) {
        AttributeEntity attributeEntity = new AttributeEntity();
        attributeEntity.setAttributeId(attributeId);
        attributeEntity.setCode(code);
        attributeEntity.setName(name);
        attributeEntity.setStatus(status);
        return attributeEntity;
    }
    
    public List<AttributesResponseDto> toAttributesResponseDtos(List<Attribute> attributes) {
        if (attributes == null || attributes.isEmpty()) {
            return new ArrayList<>();
        }
        return attributes.stream()
                         .map(attribute -> new AttributesResponseDto(
                              attribute.getAttributeId(),
                              attribute.getCode(),
                              attribute.getName(),
                              attribute.getStatus().getCodigo()))
                         .toList();
    }

    public AttributesResponseDto toAttributeResponseDto(Attribute attribute) {
        if (attribute == null) {
            return new AttributesResponseDto();
        }
        return new AttributesResponseDto(attribute.getAttributeId(),
                                         attribute.getCode(),
                                         attribute.getName(),
                                         attribute.getStatus().getCodigo());
    }
}
