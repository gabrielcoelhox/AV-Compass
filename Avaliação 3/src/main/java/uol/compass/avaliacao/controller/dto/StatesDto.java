package uol.compass.avaliacao.controller.dto;

import java.time.LocalDate;

import uol.compass.avaliacao.model.States;

public class StatesDto {

	private String name;
	private String regiao;
	private Double populacao;
	private String capital;
	private Double area;
	private LocalDate dataFundacao;
	private Integer tempoDesdeFundacao;

	public StatesDto(States states) {
		this.name = states.getName();
		this.regiao = states.getRegiao();
		this.populacao = states.getPopulacao();
		this.capital = states.getCapital();
		this.area = states.getArea();
		this.dataFundacao = states.getDataFundacao();
		this.tempoDesdeFundacao = states.getTempoDesdeFundacao();
	}
	
	public StatesDto() {
	}
	
	public StatesDto(String name, String regiao, Double populacao, String capital, Double area, LocalDate dataFundacao,
			Integer tempoDesdeFundacao) {
		this.name = name;
		this.regiao = regiao;
		this.populacao = populacao;
		this.capital = capital;
		this.area = area;
		this.dataFundacao = dataFundacao;
		this.tempoDesdeFundacao = tempoDesdeFundacao;
	}

	public String getName() {
		return name;
	}

	public String getRegiao() {
		return regiao;
	}

	public Double getPopulacao() {
		return populacao;
	}

	public String getCapital() {
		return capital;
	}

	public Double getArea() {
		return area;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public Integer getTempoDesdeFundacao() {
		return tempoDesdeFundacao;
	}
}
