package model;

public class Spa {
	private int id = 1;
	private String servico;
	private int duracao;
	private float preco;
	
	public Spa() {
		id = -1;
		servico = "none";
		duracao = 0;
		preco = 0;
	}
	public Spa(int id, String servico, float preco, int duracao) {
		setId(id);
		setServico(servico);
		setDuracao(duracao);
		setPreco(preco);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public int getId() {
		return id;
	}
	public String getServico() {
		return servico;
	}
	public int getDuracao() {
		return duracao;
	}
	public float getPreco() {
		return preco;
	}
	
	@Override
	public String toString() {
		return "Serviço: " + servico + "   Duração: " + duracao + "   Preço: R$" + preco;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Spa) obj).getId());
	}

}
