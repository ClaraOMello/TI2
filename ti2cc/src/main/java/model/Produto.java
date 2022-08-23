package model;

public class Produto {
	private int cod;
	private String nome;
	private double valor;
	private int quantidade;
	
	public Produto() {
		this.cod = -1;
		this.nome = "";
		this.valor = 0;
		this.quantidade = 0;		
	}
	
	public Produto(int cod, String nome, double valor, int quantidade) {
		setCod(cod);
		setNome(nome);
		setValor(valor);
		setQuantidade(quantidade);
	}
	
	public void setCod(int cod){
		this.cod = cod;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
	public void setValor(double valor){
		this.valor = valor;
	}
	public void setQuantidade(int quantidade){
		this.quantidade = quantidade;
	}
	
	public int getCod() {
		return cod;
	}
	public String getNome() {
		return nome;
	}
	public double getValor() {
		return valor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	
	@Override
	public String toString() {
		return "Produto [cod=" + cod + ", nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade + "]";
	}
}