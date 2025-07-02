package br.com.csc.teste_service.ports;

import java.util.List;

import br.com.csc.teste_service.exceptions.AttributeNotExistsException;
import br.com.csc.teste_service.models.Attribute;

public interface AttributeRepositoryPort {
    public void save(String attributeId,
                     String code,
                     String name,
                     String status);
    public Attribute findByCode(String code) throws AttributeNotExistsException;
    public Attribute findById(String attributeId) throws AttributeNotExistsException;
    public List<Attribute> findAll();
}