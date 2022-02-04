package entities;

public class Employee {

	private String nome;
	private Double salario;
	private Integer id;
	
	public Employee() {
	}
	
	public Employee(String nome, Double salario, Integer id) {
		this.nome = nome;
		this.salario = salario;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String toString() {
		return "id: "
			+ id 
			+ ", nome: "
			+ nome
			+ ", salário: R$"
			+ String.format("%.2f", salario);
	}
	
}
