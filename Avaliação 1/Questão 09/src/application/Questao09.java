/*
 Escreva um programa Java que tem como objetivo validar se um determinado funcion�rio
tem direito a bonifica��o. O programa precisa receber a quantidade de funcion�rios que
ser�o cadastrados e ap�s isso, o programa deve armazenar o nome e o sal�rio de cada
funcion�rio. Se o usu�rio receber at� R$ 1000,00 reais, receber� 20% de bonifica��o. Se
receber acima de 1000 e menos que 2000, recebe 10%. Se for acima de 2000, ao inv�s de
bonifica��o, o funcion�rio ter� um desconto de 10%.
No fim do programa, dever� ter uma sa�da com os nomes dos funcion�rios, sal�rio, b�nus ou
desconto e sal�rio liquido.
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

		// Cadastro de funcion�rio

		System.out.print("Quantos funcion�rios ser�o registrados? ");
		int funcionarios = sc.nextInt();

		for (int i = 1; i <= funcionarios; i++) {
			System.out.println("\n* Funcion�rio # " + i + " *");

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

		// Atualiza��o de dados

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

		// Listar funcion�rios

		System.out.println("\nLista dos funcion�rios: ");
		for (Employee empList : list) {
			System.out.println(empList);
		}
		sc.close();
	}
}
