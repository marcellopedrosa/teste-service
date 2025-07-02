package br.com.csc.teste_service.attribute.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "attribute")
@Setter
@Getter
@NoArgsConstructor
public class AttributeEntity {

	@Id
	@Column(name = "attribute_id")
	private String attributeId;
	
	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;
	
	@Column(name = "status")
	private String status;
}
