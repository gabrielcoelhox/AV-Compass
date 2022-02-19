package application;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ProdutosDao;
import model.entities.Produtos;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		ProdutosDao produtosDao = DaoFactory.createProdutosDao();
		
		System.out.println("========= XPTO System =========");
		System.out.println("1 - para INSERIR uma nova oferta");
		System.out.println("2 - para ATUALIZAR uma oferta");
		System.out.println("3 - para DELETAR uma oferta");
		System.out.println("4 - para listar as palavras que contem ?");
		System.out.println("5 - para listar um produto por id");
		System.out.println("6 - para listar todos os produtos");
		System.out.println("0 - para SAIR");
		
		System.out.print("\nDigite a opção desejada: ");
		int opcaoDesejada = sc.nextInt();
		sc.nextLine();
		
		
		// INSERT
		if (opcaoDesejada == 1) {
			System.out.println("\n=== Inserir um Produto ===");
			System.out.print("\nDigite o nome do produto: ");
			String nomeProduto = sc.nextLine();
			
			sc.nextLine();
			System.out.print("\nDigite a descrição do produto: ");
			String descricaoProduto = sc.nextLine();
			
			System.out.print("\nDigite o valor do produto: ");
			Double valorProduto = sc.nextDouble();
			
			sc.nextLine();
			System.out.print("\nDigite o valor do desconto: ");
			Double valorDesconto = sc.nextDouble();
			
			Produtos novoProduto = new Produtos(nomeProduto, descricaoProduto, valorProduto, valorDesconto, LocalDate.now());
			produtosDao.insert(novoProduto);

			System.out.println("Inserido! Novo produto = " + novoProduto.getId());
		}
		
		// UPDATE
		if (opcaoDesejada == 2) {
			// Update de um produto
			System.out.println("\n=== Update de produto ===");
			System.out.print("Digite o id do produto: ");
			int pesquisaID = sc.nextInt();
			sc.nextLine();
			Produtos produto = produtosDao.findById(pesquisaID);
			
			System.out.println(produto);
			
			System.out.print("\nNovo nome do produto: ");
			String novoNomeProduto = sc.nextLine();
			produto.setNome(novoNomeProduto);
			produtosDao.update(produto);
			
			System.out.println("Feito!");
		}
		
		// DELETAR
		if (opcaoDesejada == 3) {
			// Deletar um vendedor
			System.out.println("\n=== Deletar produto ===");
			System.out.print("Digite o id do produto que deseja deletar: ");
			int id = sc.nextInt();
			produtosDao.deleteById(id);
			System.out.println("Feito!");
		}
		
		// LISTAR ?
		if (opcaoDesejada == 4 ) {
			System.out.println("\n=== Lista que contem ? ===");
			System.out.print("Digite o nome do produto que deseja pesquisar: ");
			String pesquisaNome = sc.nextLine();
			List<Produtos> list = produtosDao.findByName(pesquisaNome);
			for (Produtos obj : list) {
				System.out.println(obj);
			}
			
		}
		
		// LISTAR POR ID
		if (opcaoDesejada == 5) {
			System.out.println("\n=== Produto por Id ===");
			System.out.print("Digite o id do produto: ");
			int pesquisaID = sc.nextInt();
			Produtos produto = produtosDao.findById(pesquisaID);

			System.out.println(produto);
		}
		
		// LISTAR ALL
		if (opcaoDesejada == 6) {
			System.out.println("\n=== Listar Todos os Produtos ===");
			List<Produtos> list = produtosDao.findAll();
			list = produtosDao.findAll();
			for (Produtos obj : list) {
				System.out.println(obj);
			}
		}
		
		// SAIR
		if (opcaoDesejada == 0) {
			System.out.println("\nFechando!");
			System.exit(0);
		}
		sc.close();
	}
}
