package uol.compass.avaliacao.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_states")
public class States {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "regiao")
	private String regiao;

	@Column(name = "populacao")
	private Double populacao;

	@Column(name = "capital")
	private String capital;

	@Column(name = "area")
	private Double area;

	@Column(name = "dataDeFundacao")
	private LocalDate dataFundacao;

	@Column(name = "tempoDesdeFundacao")
	private Integer tempoDesdeFundacao;

	public States() {
	}

	public States(Long id, String name, String regiao, Double populacao, String capital, Double area,
			LocalDate dataFundacao, Integer tempoDesdeFundacao) {
		this.id = id;
		this.name = name;
		this.regiao = regiao;
		this.populacao = populacao;
		this.capital = capital;
		this.area = area;
		this.dataFundacao = dataFundacao;
		this.tempoDesdeFundacao = tempoDesdeFundacao;
	}

	public Long getId() {
		return id;
	}

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
}
