package uol.compass.avaliacao.config.enums;

import java.util.Arrays;

public enum Sex {

	Masculino("MASCULINO"),
	Feminino("FEMININO");
	
	private String descricao;

	Sex(String descricao) {
		this.descricao = descricao;
	}

	public static Sex fromDescricao(String name) {
		return Arrays.stream(Sex.values())
				.filter(a -> name.equalsIgnoreCase(a.name()))
				.findFirst()
				.orElseThrow();
	}
}
