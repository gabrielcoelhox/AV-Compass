/*
Emoticons são símbolos usados para expressar o sentimento de quem escreve uma
mensagem. Scott Fahlman, um professor de uma universidade americana, foi o primeiro a
propor o uso das sequências de caracteres :-) e :-(, que viraram respectivamente símbolos
para “divertido” e “chateado”.
Nosso cliente, está criando uma aplicação que basicamente quer saber como seus
funcionários estão se sentindo baseado nos emoticons citados acima e digitados por eles.
Dada uma mensagem composta por uma cadeia de caracteres, escreva um programa para
determinar o sentimento expresso na mensagem. O seu programa deve usar a classe
Scanner para ler uma linha de entrada e a partir dessa linha devolver o sentimento expresso
e salvar no banco de dados.

a) neutro: se o número de símbolos “divertido” é igual ao número de símbolos “chateado”
b) divertido: se o número de símbolos “divertido” é maior do que número de símbolos
“chateado”
c) chateado: se o número de símbolos “chateado” é maior do que número de símbolos
“divertido”.
*/

package Questao10;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String divertido = ":-)";
		String chateado = ":-(";

		System.out.println("Digite a frase: ");
		String valor1 = sc.nextLine();
		String valor2 = valor1;

		int i, r_chateado = 0, r_divertido = 0;

		try {
			i = valor1.indexOf(divertido);
			while (i != -1) {
				r_divertido++;
				valor1 = valor1.substring(i + 1);
				i = valor1.indexOf(divertido);
			}
		} catch (InputMismatchException ex) {
			System.out.println("Caractere inválido, tente novamente");
			ex.printStackTrace();
		}

		try {
			i = valor2.indexOf(chateado);
			while (i != -1) {
				r_chateado++;
				valor2 = valor2.substring(i + 1);
				i = valor2.indexOf(chateado);
			}
		} catch (InputMismatchException e) {
			System.out.println("Caractere inválido, tente novamente");
			e.printStackTrace();
		}

		if (r_divertido > r_chateado) {
			System.out.println("\ndivertido");
		} else if (r_divertido < r_chateado) {
			System.out.println("\nchateado");
		} else {
			System.out.println("\nneutro");
		}
		
		sc.close();
	}
}
