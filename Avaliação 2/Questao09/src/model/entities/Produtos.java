package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Produtos implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String descricao;
	private Double valor;
	private Double desconto;
	private LocalDate dataInicio;
	

	public Produtos() {
	}
	
	public Produtos(String nome, String descricao, Double valor, Double desconto, LocalDate dataInicio) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.desconto = desconto;
		this.dataInicio = dataInicio;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produtos other = (Produtos) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Id = " + id + ", nome = " + nome + 
				", descricao = " + descricao +
				", valor = R$" + valor +
				", desconto = " + desconto + "%";
	}
}
