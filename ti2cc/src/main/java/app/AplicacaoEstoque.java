package app;

import java.util.List;
import java.util.Scanner;

import dao.DAO;

import dao.ProdutoDAO;
import model.Produto;

public class AplicacaoEstoque {
	public static Scanner scan = new Scanner(System.in);

	public void menu() {
		System.out.println("1 - Listar");
		System.out.println("2 - Inserir");
		System.out.println("3 - Excluir");
		System.out.println("4 - Atualizar");
		System.out.println("5 - Sair");
	}
	
	public void listar() {
		
	}
	public static void inserir(ProdutoDAO produtoDAO) {
		System.out.println("\n\n==== Inserir produto === ");
		int nCod, nQuant;
		String nNome;
		Double nValor;		
		Produto produto; 
		System.out.print("Codigo: ");
		nCod = scan.nextInt();
		System.out.println("Nome do produto: ");
		nNome = scan.nextLine();
		System.out.print("Valor unitario: ");
		nValor = scan.nextDouble();
		System.out.print("Quantidade em estoque: ");
		nQuant = scan.nextInt();
		
		
		produto = new Produto(nCod, nNome, nValor, nQuant);
		if(produtoDAO.insert(produto) == true) {
			System.out.println("Inserção com sucesso -> " + produto.toString());
		}
	}
		
	public static void excluir() {
		
	}
	public static void atualizar() {
		
	}
	
	public static void main(String[] args) throws Exception {
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		inserir(produtoDAO);
		
		System.out.println("\n\n==== Mostrar produtos menos de 10 unidades === ");
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

		System.out.println("\n\n==== Atualizar senha (código (" + usuario.getCodigo() + ") === ");
		usuario.setSenha(DAO.toMD5("pablo"));
		usuarioDAO.update(usuario);
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", DAO.toMD5("pablo")));		
		
		System.out.println("\n\n==== Invadir usando SQL Injection ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", "x' OR 'x' LIKE 'x"));

		System.out.println("\n\n==== Mostrar usuários ordenados por código === ");
		usuarios = usuarioDAO.getOrderByCodigo();
		for (Usuario u: usuarios) {
			System.out.println(u.toString());
		}
		*/
		
//		System.out.println("\n\n==== Excluir usuário (código " + produto.getCod() + ") === ");
//		produtoDAO.delete(produto.getCod());
		/*
		System.out.println("\n\n==== Mostrar usuários ordenados por login === ");
		usuarios = usuarioDAO.getOrderByLogin();
		for (Usuario u: usuarios) {
			System.out.println(u.toString());
		}*/
	}
}