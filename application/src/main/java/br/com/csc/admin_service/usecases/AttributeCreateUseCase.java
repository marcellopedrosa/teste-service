package br.com.csc.admin_service.usecases;

import br.com.csc.admin_service.exceptions.AttributeExistsException;
import br.com.csc.admin_service.exceptions.AttributeNotExistsException;
import br.com.csc.admin_service.exceptions.BLLException;
import br.com.csc.admin_service.models.Attribute;
import br.com.csc.admin_service.ports.AttributeRepositoryPort;

public class AttributeCreateUseCase {
    
    private final AttributeRepositoryPort attributeRepositoryPort;
    private final AttributeExistsByCodeUseCase existsAttributeByCodeUseCase;    

    public AttributeCreateUseCase(AttributeRepositoryPort attributeRepositoryPort,
                                  AttributeExistsByCodeUseCase existsAttributeByNameUseCase) {
        this.attributeRepositoryPort = attributeRepositoryPort;
        this.existsAttributeByCodeUseCase = existsAttributeByNameUseCase;
    }

    public void execute(Attribute attribute) throws BLLException {

        try {
            this.existsAttributeByCodeUseCase.execute(attribute.getCode());

        } catch (AttributeExistsException e) {
            throw new AttributeExistsException(e.getMessage());

        } catch (AttributeNotExistsException e) {
            attributeRepositoryPort.save(attribute.getAttributeId(),
                                         attribute.getCode(),
                                         attribute.getName(),
                                         attribute.getStatus().getCodigo());
        }
    }
}
