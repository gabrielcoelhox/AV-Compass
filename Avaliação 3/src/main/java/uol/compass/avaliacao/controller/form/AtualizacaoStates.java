package uol.compass.avaliacao.controller.form;

import java.time.LocalDate;

import uol.compass.avaliacao.model.States;
import uol.compass.avaliacao.repository.StatesRepository;

public class AtualizacaoStates {

	private String name;
	private String regiao;
	private Double populacao;
	private String capital;
	private Double area;
	private LocalDate dataFundacao;
	private Integer tempoDesdeFundacao;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public Double getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Double populacao) {
		this.populacao = populacao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}
	
	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	public Integer getTempoDesdeFundacao() {
		return tempoDesdeFundacao;
	}

	public void setTempoDesdeFundacao(Integer tempoDesdeFundacao) {
		this.tempoDesdeFundacao = tempoDesdeFundacao;
	}

	public States atualizar(Long id, StatesRepository statesRepository) {
		States state = statesRepository.getById(id);

		state.setName(this.name);
		state.setRegiao(this.regiao);
		state.setPopulacao(this.populacao);
		state.setCapital(this.capital);
		state.setArea(this.area);
		state.setDataFundacao(this.dataFundacao);
		state.setTempoDesdeFundacao(this.tempoDesdeFundacao);
	
		return state;
	}
}
