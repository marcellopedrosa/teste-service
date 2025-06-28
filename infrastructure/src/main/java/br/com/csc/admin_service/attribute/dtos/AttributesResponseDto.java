package br.com.csc.admin_service.attribute.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributesResponseDto {
    private String attributeId;
    private String code;
    private String name;
    private String status;
    public AttributesResponseDto() {}
    public AttributesResponseDto(String attributeId, String code, String name, String status) {
        this.attributeId = attributeId;
        this.code = code;
        this.name = name;
        this.status = status;
    }
}
