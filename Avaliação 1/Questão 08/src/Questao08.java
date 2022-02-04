/* 
Escreva um programa Java que tem como objetivo validar se um usu�rio e senha existem
no sistema. Se o usu�rio e senha tiver corretos, o usu�rio dever� receber uma mensagem de
acordo com o hor�rio.
*/

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Questao08 {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

		// Usu�rio e Senha

		Map<String, String> loginMap = new HashMap<>();
		loginMap.put("gabriel", "12345");

		List<Map<String, String>> listaLogin = Arrays.asList(loginMap);

		// Entrada de dados

		System.out.println("Ol�, seja bem vindo ao painel de login.\n");

		System.out.print("Digite o seu usu�rio: ");
		String loginUsuario = sc.next();

		System.out.print("Digite a sua senha: ");
		String senhaUsuario = sc.next();

		for (Map<String, String> login : listaLogin) {
			String usuario = login.keySet().toArray()[0].toString();

			String senha = login.get(usuario);
			if (loginUsuario.equals(usuario) && senhaUsuario.equals(senha)) {
				System.out.println("\nLogin efetuado com sucesso!");
			} else {
				System.out.println("Usu�rio e/ou senha incorretos!\n");
			}
		}

		LocalTime midnight = LocalTime.MIDNIGHT;
		LocalDate today = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
		LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);

		LocalDateTime dataAtual = LocalDateTime.now();

		if (dataAtual.isAfter(todayMidnight) && dataAtual.isBefore(todayMidnight.plusHours(6))) {
			System.out.println("Boa madrugada, voc� se logou ao nosso sistema.");
		} else if (dataAtual.isAfter(todayMidnight.plusHours(6)) && dataAtual.isBefore(todayMidnight.plusHours(12))) {
			System.out.println("Bom dia, voc� se logou ao nosso sistema.");
		} else if (dataAtual.isAfter(todayMidnight.plusHours(12)) && dataAtual.isBefore(todayMidnight.plusHours(18))) {
			System.out.println("Boa tarde, voc� se logou ao nosso sistema.");
		} else if (dataAtual.isAfter(todayMidnight.plusHours(18)) && dataAtual.isBefore(todayMidnight.plusHours(24))) {
			System.out.println("Boa noite, voc� se logou ao nosso sistema.");
		}
		sc.close();
	}
}
