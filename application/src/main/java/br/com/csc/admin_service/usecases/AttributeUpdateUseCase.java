package br.com.csc.admin_service.usecases;

import br.com.csc.admin_service.exceptions.AttributeExistsException;
import br.com.csc.admin_service.exceptions.AttributeNotExistsException;
import br.com.csc.admin_service.exceptions.BLLException;
import br.com.csc.admin_service.models.Attribute;
import br.com.csc.admin_service.ports.AttributeRepositoryPort;

public class AttributeUpdateUseCase {
    
    private final AttributeRepositoryPort attributeRepositoryPort;

    public AttributeUpdateUseCase(AttributeRepositoryPort attributeRepositoryPort) {
        this.attributeRepositoryPort = attributeRepositoryPort;
    }

    public void execute(Attribute attribute) throws BLLException {

        try {

            Attribute attributeDb = attributeRepositoryPort.findByCode(attribute.getCode());

            //se nao bater o id é porque existe outro atributo com o codigo informado
            if (attribute.getAttributeId().equals(attributeDb.getAttributeId())) {
                attributeRepositoryPort.save(attribute.getAttributeId(),
                attribute.getCode(),
                attribute.getName(),
                attribute.getStatus().getCodigo());
            } else {
                throw new AttributeExistsException("Existe outro atributo com id ["+attributeDb.getAttributeId()+"] que já possui o código: "+attributeDb.getCode());
            }

        } catch (AttributeNotExistsException e) {
            throw new BLLException(e.getMessage());
        }
    }
}
