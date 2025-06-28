package br.com.csc.admin_service.usecases;

import br.com.csc.admin_service.exceptions.AttributeExistsException;
import br.com.csc.admin_service.exceptions.BLLException;
import br.com.csc.admin_service.ports.AttributeRepositoryPort;

public class AttributeExistsByCodeUseCase {
    
    private final AttributeRepositoryPort attributeRepositoryPort;

    public AttributeExistsByCodeUseCase(AttributeRepositoryPort attributeRepositoryPort) {
        this.attributeRepositoryPort = attributeRepositoryPort;
    }

    public void execute(String code) throws BLLException {
        attributeRepositoryPort.findByCode(code);
        throw new AttributeExistsException("Já existe um atributo com o código: " + code);
    }
}
