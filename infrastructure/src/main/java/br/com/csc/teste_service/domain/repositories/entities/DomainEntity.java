package br.com.csc.teste_service.domain.repositories.entities;

import br.com.csc.teste_service.attribute.repositories.entities.AttributeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "domain")
@Setter
@Getter
@NoArgsConstructor
public class DomainEntity {

	@Id
	@Column(name = "domain_id")
	private String domainId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "value")
	private String value;

	@Column(name = "obs")
	private String obs;

	@Column(name = "status")
	private String status; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
    private AttributeEntity attributeEntity;
}
