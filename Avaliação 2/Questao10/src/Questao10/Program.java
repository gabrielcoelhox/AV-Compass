/*
Emoticons s�o s�mbolos usados para expressar o sentimento de quem escreve uma
mensagem. Scott Fahlman, um professor de uma universidade americana, foi o primeiro a
propor o uso das sequ�ncias de caracteres :-) e :-(, que viraram respectivamente s�mbolos
para �divertido� e �chateado�.
Nosso cliente, est� criando uma aplica��o que basicamente quer saber como seus
funcion�rios est�o se sentindo baseado nos emoticons citados acima e digitados por eles.
Dada uma mensagem composta por uma cadeia de caracteres, escreva um programa para
determinar o sentimento expresso na mensagem. O seu programa deve usar a classe
Scanner para ler uma linha de entrada e a partir dessa linha devolver o sentimento expresso
e salvar no banco de dados.

a) neutro: se o n�mero de s�mbolos �divertido� � igual ao n�mero de s�mbolos �chateado�
b) divertido: se o n�mero de s�mbolos �divertido� � maior do que n�mero de s�mbolos
�chateado�
c) chateado: se o n�mero de s�mbolos �chateado� � maior do que n�mero de s�mbolos
�divertido�.
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
			System.out.println("Caractere inv�lido, tente novamente");
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
			System.out.println("Caractere inv�lido, tente novamente");
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
