package uol.compass.avaliacao.controller.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import uol.compass.avaliacao.model.Estado;
import uol.compass.avaliacao.model.Regioes;

public class EstadoDto {

	private long id;
	private String nome;
	private Regioes regiao;
	private int populacao;
	private String capital;
	private double area;
	private LocalDate dataDeFundacao;
	private int tempoDesdeFundacao;

	public EstadoDto(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
		this.regiao = estado.getRegiao();
		this.populacao = estado.getPopulacao();
		this.capital = estado.getCapital();
		this.area = estado.getArea();
		this.dataDeFundacao = estado.getDataDeFundacao();
		this.tempoDesdeFundacao = estado.getTempoDesdeFundacao();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Regioes getRegiao() {
		return regiao;
	}

	public void setRegiao(Regioes regiao) {
		this.regiao = regiao;
	}

	public int getPopulacao() {
		return populacao;
	}

	public void setPopulacao(int populacao) {
		this.populacao = populacao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public LocalDate getDataDeFundacao() {
		return dataDeFundacao;
	}

	public void setDataDeFundacao(LocalDate dataDeFundacao) {
		this.dataDeFundacao = dataDeFundacao;
	}

	public int getTempoDesdeFundacao() {
		return tempoDesdeFundacao;
	}

	public void setTempoDesdeFundacao(int tempoDesdeFundacao) {
		this.tempoDesdeFundacao = tempoDesdeFundacao;
	}

	public static Page<EstadoDto> converter(Page<Estado> estados) {
		return estados.map(EstadoDto::new);
	}

}
