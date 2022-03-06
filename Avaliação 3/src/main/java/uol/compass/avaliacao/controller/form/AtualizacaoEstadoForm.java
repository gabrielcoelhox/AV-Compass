package uol.compass.avaliacao.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import uol.compass.avaliacao.model.Estado;
import uol.compass.avaliacao.repository.EstadoRepository;

public class AtualizacaoEstadoForm {

	@NotNull
	private int populacao;
	@NotNull
	@NotEmpty
	private String capital;
	@NotNull
	private double area;

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

	public Estado atualizar(Long id, EstadoRepository estadoRespository) {
		Estado estado = estadoRespository.getById(id);
		estado.setPopulacao(this.populacao);
		estado.setCapital(this.capital);
		estado.setArea(this.area);
		
		return estado;
	}
}
