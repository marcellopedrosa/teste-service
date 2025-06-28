package br.com.csc.admin_service.attribute.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.csc.admin_service.attribute.dtos.AttributeCreateRequestDto;
import br.com.csc.admin_service.attribute.dtos.AttributeUpdateRequestDto;
import br.com.csc.admin_service.attribute.dtos.AttributesResponseDto;
import br.com.csc.admin_service.attribute.mappers.AttributeMapper;
import br.com.csc.admin_service.exceptions.AttributeException;
import br.com.csc.admin_service.exceptions.AttributeExistsException;
import br.com.csc.admin_service.exceptions.AttributeNotExistsException;
import br.com.csc.admin_service.exceptions.BLLException;
import br.com.csc.admin_service.models.Attribute;
import br.com.csc.admin_service.ports.AttributeRepositoryPort;
import br.com.csc.admin_service.usecases.AttributeCreateUseCase;
import br.com.csc.admin_service.usecases.AttributeFindByIdUseCase;
import br.com.csc.admin_service.usecases.AttributeUpdateUseCase;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/attribute")
//@PreAuthorize("hasRole('api_access')")
public class AttributeController {
    
    @Inject
    private AttributeMapper attributeMapper;
    
    @Inject
    private AttributeCreateUseCase attributeCreateUseCase; 

    @Inject
    private AttributeRepositoryPort attributeRepositoryPort;

    @Inject
    private AttributeFindByIdUseCase attributeFindUseCase;
    
    @Inject
    private AttributeUpdateUseCase attributeUpdateUseCase;

    @PostMapping("/v1")
    public ResponseEntity<?> createAttribute(@RequestBody @Valid AttributeCreateRequestDto attributeCreateRequestDto) throws BLLException {

        try {
            Attribute attribute = attributeMapper.toAttributeNewModel(attributeCreateRequestDto);
            this.attributeCreateUseCase.execute(attribute);
            return ResponseEntity.ok()
                                 .body(attribute);
        } catch (AttributeExistsException e) {
            throw new BLLException(e.getMessage());
        }
    }

    @PutMapping("/v1")
    public ResponseEntity<?> updateAttribute(@RequestBody @Valid AttributeUpdateRequestDto attributeUpdateRequestDto)
            throws BLLException {

        try {
            Attribute attribute = attributeMapper.toAttributeUpdateModel(attributeUpdateRequestDto);
            this.attributeUpdateUseCase.execute(attribute);
            return ResponseEntity.ok()
                    .body(attribute);
        } catch (AttributeExistsException e) {
            throw new BLLException(e.getMessage());
        }
    }

    @GetMapping("/v1/grid")
    public ResponseEntity<?> getAttributes() {
        List<Attribute> list = this.attributeRepositoryPort.findAll();
        List<AttributesResponseDto> response = this.attributeMapper.toAttributesResponseDtos(list);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/v1/{attributeId}")
    public ResponseEntity<?> getAttributeDetail(@PathVariable String attributeId) throws BLLException {
        try {
            Attribute attribute = this.attributeFindUseCase.execute(attributeId);
            return ResponseEntity.ok().body(this.attributeMapper.toAttributeResponseDto(attribute));
        } catch (AttributeException | AttributeNotExistsException e) {
            throw new BLLException(e.getMessage());
        } 
    }
}
