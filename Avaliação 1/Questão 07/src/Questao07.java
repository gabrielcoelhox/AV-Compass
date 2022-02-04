/* 
Escreva um programa Java que tem como objetivo ser um quis de perguntas e respostas.
Na tela, voc� perguntar� ao usu�rio e ele responder�. Se o usu�rio acertar ou errar, voc�
precisa informar se ele acertou ou errou e passar para pr�xima pergunta (enquanto houver
pr�xima). Utilizem array para guardar as perguntas e respostas.
No fim do quis, voc� deve apresentar quantas o usu�rio acertou e quantas ele errou, como
no exemplo abaixo(n�o precisa ser exatamente assim):

Ex:
Usuario XPTO
Acertos: 6
Erros: 4
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Questao07 {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		// Quest�es
		
		Map<String, String> questao1 = new HashMap<>();
		questao1.put("Quest�o 1 - Qual o nome da empresa que criou o Java?\n" +
				"(A) Sun Microsystems, empresa comprada pela Oracle em 2009\n" +
                "(B) Google, criadora de diversas outras linguagens\n" +
                "(C) Microsoft, em 2008\n" +
                "(D) Java Corp, empresa vendida para Google\n\n" , "a");
                
		Map<String, String> questao2 = new HashMap<>();
		questao2.put("Quest�o 2 - O que significa a sigla API?\n" +
				"(A) Applications Per Interface\n" +
                "(B) Applications Programming Inteconection\n" +
                "(C) Applications Programming Interface\n" +
                "(D) Applications Paralelo Internal\n\n", "c");
		
		Map<String, String> questao3 = new HashMap<>();
		questao3.put("Quest�o 3 - Quantas casas decimais tem o n�mero pi?\n" +
				"(A) Duas\n" +
				"(B) Centenas\n" +
				"(C) Infinitas\n" +
				"(D) Milhares\n\n", "c");
		
		Map<String, String> questao4 = new HashMap<>();
		questao4.put("Quest�o 4 - Quantas finais de Copas o Brasil j� perdeu?\n" +
				"(A) 2 finais\n" +
				"(B) 3 finais\n" +
				"(C) 4 finais\n" +
				"(D) 1 final\n\n", "a");
		
		Map<String, String> questao5 = new HashMap<>();
		questao5.put("Quest�o 5 - Qual o maior artilheiro da Sele��o Brasileira em copas?\n" +
				"(A) Garrincha\n" +
				"(B) Ronaldo\n" +
				"(C) Zico\n" +
				"(D) Pel�\n\n", "d");
                
		List<Map<String, String>> questoes = Arrays.asList(questao1, questao2, questao3, questao4, questao5);
		
		System.out.println("Ol�, seja bem vindo ao quiz de perguntas e respostas.\n");
		
		int qtdErros = 0;
		int qtdAcertos = 0;
		
		for (Map <String, String> questao : questoes) {
			String pergunta = questao.keySet().toArray()[0].toString();
			
			System.out.println(pergunta);
			
			System.out.print("Digite a op��o desejada: ");
			String respInput = sc.nextLine();	
			
			String resposta = questao.get(pergunta);
			if (respInput.toLowerCase().equals(resposta)) {
				System.out.println("Voc� acertou!\n");
				qtdAcertos++;
			} else {
				System.out.println("Voc� errou!\n");
				qtdErros++;
			}
		}
		
		System.out.println("Voc� acertou: " + qtdAcertos + " quest�es");
		System.out.println("Voc� errou: " + qtdErros + " quest�es");
		sc.close();
	}
}
