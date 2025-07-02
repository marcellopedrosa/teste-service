package br.com.csc.teste_service.attribute.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeUpdateRequestDto {

    @NotNull(message = "O campo 'attributeId' é obrigatório.")
    @NotBlank(message = "O campo 'attributeId' não pode estar em branco.")
    private String attributeId;

    @NotNull(message = "O campo 'name' é obrigatório.")
    @NotBlank(message = "O campo 'name' não pode estar em branco.")
    private String name;

    @NotNull(message = "O campo 'code' é obrigatório.")
    @NotBlank(message = "O campo 'code' não pode estar em branco.")
    private String code;

    @NotNull(message = "O campo 'status' é obrigatório.")
    @NotBlank(message = "O campo 'status' não pode estar em branco.")
    @Pattern(regexp = "^(A|I)$", message = "O campo 'status' deve conter apenas os valores 'A' ou 'I'.")
    private String status;

    public AttributeUpdateRequestDto() {
    }

    public AttributeUpdateRequestDto(String attributeId,
                                     String code,
                                     String name,
                                     String status) {
        this.attributeId = attributeId;
        this.code = code;
        this.name = name;
        this.status = status;
    }
}
