package uol.compass.avaliacao.config.enums;

import java.util.Arrays;

public enum Ideology {

	Direita("DIREITA"),
	Centro("CENTRO"),
	Esquerda("ESQUERDA");
	
	private String ideologia;

	Ideology(String ideologia) {
		this.ideologia = ideologia;
	}
	
	public static Ideology fromDescricao(String name) {
		return Arrays.stream(Ideology.values())
				.filter(a -> name.equalsIgnoreCase(a.name()))
				.findFirst()
				.orElseThrow();
	}
}