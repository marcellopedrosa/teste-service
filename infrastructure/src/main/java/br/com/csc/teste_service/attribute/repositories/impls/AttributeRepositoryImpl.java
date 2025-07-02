package br.com.csc.teste_service.attribute.repositories.impls;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.csc.teste_service.attribute.mappers.AttributeMapper;
import br.com.csc.teste_service.attribute.repositories.adapters.jpa.postgres.AttributeRepositorySpring;
import br.com.csc.teste_service.attribute.repositories.entities.AttributeEntity;
import br.com.csc.teste_service.exceptions.AttributeNotExistsException;
import br.com.csc.teste_service.models.Attribute;
import br.com.csc.teste_service.ports.AttributeRepositoryPort;
import jakarta.inject.Inject;

@Component
public class AttributeRepositoryImpl implements AttributeRepositoryPort {

    @Inject
    private AttributeRepositorySpring attributeRepositorySpring;

    @Inject
    private AttributeMapper attributeMapper;

    @Override
    public void save(String attributeId, String code, String name, String status) {
        AttributeEntity attributeEntity = attributeMapper.toAttributeEntity(attributeId, code, name, status);
        this.attributeRepositorySpring.save(attributeEntity);
    }

    @Override
    public Attribute findByCode(String code) throws AttributeNotExistsException {
        AttributeEntity attributeEntity = this.attributeRepositorySpring.findByCode(code);
        if (attributeEntity == null) {
           throw new AttributeNotExistsException("Atributo não localizado pelo código: " + code);
        }
        return this.attributeMapper.toAttributeModel(attributeEntity);
    }

    @Override
    public List<Attribute> findAll() {
       return this.attributeRepositorySpring.findAll()
                                            .stream()
                                            .map(attributeMapper::toAttributeModel)
                                            .toList();
    }

    @Override
    public Attribute findById(String attributeId) throws AttributeNotExistsException {
       Optional<AttributeEntity> attributeEntity = this.attributeRepositorySpring.findById(attributeId);
       if(!attributeEntity.isPresent()){
            throw new AttributeNotExistsException("Atributo não localizado no banco de dados pelo id "+attributeId);
       }
       return this.attributeMapper.toAttributeModel(attributeEntity.get());
    }
}
