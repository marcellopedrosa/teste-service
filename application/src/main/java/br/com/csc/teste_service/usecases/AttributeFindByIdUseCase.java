package br.com.csc.teste_service.usecases;

import br.com.csc.teste_service.exceptions.AttributeException;
import br.com.csc.teste_service.exceptions.BLLException;
import br.com.csc.teste_service.models.Attribute;
import br.com.csc.teste_service.ports.AttributeRepositoryPort;

public class AttributeFindByIdUseCase {
    
    private final AttributeRepositoryPort attributeRepositoryPort;

    public AttributeFindByIdUseCase(AttributeRepositoryPort attributeRepositoryPort) {
        this.attributeRepositoryPort = attributeRepositoryPort;
    }

    public Attribute execute(String attributeId) throws BLLException {

        if (attributeId==null || attributeId.trim().isEmpty()) {
            throw new AttributeException("O id do atributo é obrigatório para realizar a consulta.");
        }

        return this.attributeRepositoryPort.findById(attributeId);
    }
}
