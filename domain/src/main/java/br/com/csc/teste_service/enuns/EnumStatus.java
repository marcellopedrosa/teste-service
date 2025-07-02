package br.com.csc.teste_service.enuns;

public enum EnumStatus {

	ATIVO ("A","Ativo"),
	INATIVO("I", "Inativo");
	
	private String codigo;
	private String descricao;
	
	EnumStatus(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EnumStatus get(String codigo) {
		for (EnumStatus item : EnumStatus.values()) {
			if (codigo.trim().equals(item.getCodigo())) {
				return item;
			}
		}
		throw new IllegalArgumentException("O código " + codigo + " para EnumStatus é inválido");
	}
}
