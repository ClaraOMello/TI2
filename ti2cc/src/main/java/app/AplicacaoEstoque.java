package app;

import java.util.List;
import java.util.Scanner;

import dao.DAO;

import dao.ProdutoDAO;
import model.Produto;

public class AplicacaoEstoque {
	public static Scanner scan = new Scanner(System.in);

	public static int menu() {
		int op;
		System.out.println("1 - Listar");
		System.out.println("2 - Inserir");
		System.out.println("3 - Excluir");
		System.out.println("4 - Atualizar");
		System.out.println("5 - Sair");
		
		op = scan.nextInt();
		
		return op;
	}
	
	public static void listar(ProdutoDAO produtoDAO) {
		List<Produto> produtos;
		
		System.out.println("\n\n==== Mostrar produtos ordenados por código === ");
		produtos = produtoDAO.getOrderByCod();
		for (Produto p: produtos) {
			System.out.println(p.toString());
		}
	}
	
	public static void inserir(ProdutoDAO produtoDAO) {
		System.out.println("\n\n==== Inserir produto === ");
		int nCod, nQuant;
		String nNome;
		Double nValor;		
		Produto produto; 
		System.out.print("Codigo: ");
		nCod = scan.nextInt();
		nNome = scan.nextLine();
		System.out.println("Nome do produto: ");
		nNome = scan.nextLine();
		System.out.print("Valor unitario: ");
		nValor = scan.nextDouble();
		System.out.print("Quantidade em estoque: ");
		nQuant = scan.nextInt();
		
		
		produto = new Produto(nCod, nNome, nValor, nQuant);
		if(produtoDAO.insert(produto) == true) {
			System.out.println("Inserido com sucesso -> " + produto.toString());
		}
	}
		
	public static void excluir(ProdutoDAO produtoDAO) {
		String conf;
		int codigo;
		Produto produto;
		
		System.out.println("\n\n==== Excluir produto ==== ");
		
		System.out.println("Codigo do produto: ");
		codigo = scan.nextInt();
		
		produto = produtoDAO.get(codigo);
		
		System.out.println(produto.toString());
		System.out.println("Excluir? s/n ");
		
		conf = scan.next();
		
		if (conf.charAt(0) == 's' && produtoDAO.delete(codigo)) System.out.println("Sucesso!") ;
		else System.out.println("Cancelado!");
	}
	
	public static void atualizar(ProdutoDAO produtoDAO) {
		String conf;
		int codigo, op;
		Produto produto;
		int nQuant;
		String nNome;
		Double nValor;	
		
		System.out.println("\n\n==== Atualizar Produto ==== ");
		
		System.out.println("Codigo do produto: ");
		codigo = scan.nextInt();
		
		produto = produtoDAO.get(codigo);
		
		System.out.println("Alterar:\n 1 - Tudo\n 2 - Nome\n 3 - Valor\n 4 - Quantidade");
		
		op = scan.nextInt();
		
		if (op == 2)
		{
			nNome = scan.nextLine();
			System.out.println("Nome: ");
			nNome = scan.nextLine();
			
			produto.setNome(nNome);
		}
		else if (op == 3)
		{
			System.out.print("Valor: ");
			nValor = scan.nextDouble();
			
			produto.setValor(nValor);
		}
		else if(op == 4)
		{
			System.out.print("Quantidade: ");
			nQuant = scan.nextInt();
			
			produto.setQuantidade(nQuant);
		}
		else {
			nNome = scan.nextLine();
			System.out.println("Nome: ");
			nNome = scan.nextLine();
			System.out.print("Valor: ");
			nValor = scan.nextDouble();
			System.out.print("Quantidade: ");
			nQuant = scan.nextInt();
			
			produto.setNome(nNome);
			produto.setValor(nValor);
			produto.setQuantidade(nQuant);
		}
		
		
		System.out.println(produto.toString());
		System.out.println("Alterar? s/n ");
		conf = scan.next();
		
		if (conf.charAt(0) == 's' && produtoDAO.update(produto)) System.out.println("Sucesso!") ;
		else System.out.println("Cancelado!");
	}
	
	public static void main(String[] args) throws Exception {
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		int op;
		
		do {
			op = menu();
			if (op == 1) listar(produtoDAO);
			else if (op == 2) inserir(produtoDAO);
			else if (op == 3) excluir(produtoDAO);
			else if (op == 4) atualizar(produtoDAO);
			
		}while (op != 5);
		
		
		
		System.out.println("\n\n==== Mostrar produtos com menos de 10 unidades === ");
		List<Produto> produtos = produtoDAO.getAcabando();
		for (Produto p: produtos) {
			System.out.println(p.toString());
		}
		
		/*
			
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", "pablo"));
			
		System.out.println("\n\n==== Mostrar usuários do sexo masculino === ");
		List<Usuario> usuarios = usuarioDAO.getSexoMasculino();
		for (Usuario u: usuarios) {
			System.out.println(u.toString());
		}
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", DAO.toMD5("pablo")));		
		
		System.out.println("\n\n==== Invadir usando SQL Injection ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", "x' OR 'x' LIKE 'x"));


		System.out.println("\n\n==== Mostrar usuários ordenados por login === ");
		usuarios = usuarioDAO.getOrderByLogin();
		for (Usuario u: usuarios) {
			System.out.println(u.toString());
		}*/
	}
}