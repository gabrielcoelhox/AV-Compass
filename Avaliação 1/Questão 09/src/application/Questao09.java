/*
 Escreva um programa Java que tem como objetivo validar se um determinado funcionário
tem direito a bonificação. O programa precisa receber a quantidade de funcionários que
serão cadastrados e após isso, o programa deve armazenar o nome e o salário de cada
funcionário. Se o usuário receber até R$ 1000,00 reais, receberá 20% de bonificação. Se
receber acima de 1000 e menos que 2000, recebe 10%. Se for acima de 2000, ao invés de
bonificação, o funcionário terá um desconto de 10%.
No fim do programa, deverá ter uma saída com os nomes dos funcionários, salário, bônus ou
desconto e salário liquido.
 */

package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Questao09 {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Employee> list = new ArrayList<>();

		// Cadastro de funcionário

		System.out.print("Quantos funcionários serão registrados? ");
		int funcionarios = sc.nextInt();

		for (int i = 1; i <= funcionarios; i++) {
			System.out.println("\n* Funcionário # " + i + " *");

			System.out.print("Id: ");
			Integer id = sc.nextInt();

			System.out.print("Nome: ");
			sc.nextLine();
			String nome = sc.nextLine();

			System.out.print("Salario: ");
			Double salario = sc.nextDouble();

			Employee emp = new Employee(nome, salario, id);

			list.add(emp);
		}

		// Atualização de dados

		for (Employee x : list) {
			if(x.getSalario() <= 1000.0) {
				Double resultado = x.getSalario() * 1.2;
				x.setSalario(resultado);
			} else if (x.getSalario() > 1000 && x.getSalario() < 2000) {
				Double resultado = x.getSalario() * 1.1;
				x.setSalario(resultado);
			} if (x.getSalario() >= 2000) {
				Double resultado = x.getSalario() * 0.9;
				x.setSalario(resultado);
			}
		}

		// Listar funcionários

		System.out.println("\nLista dos funcionários: ");
		for (Employee empList : list) {
			System.out.println(empList);
		}
		sc.close();
	}
}
