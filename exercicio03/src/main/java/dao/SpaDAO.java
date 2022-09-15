package dao;

import model.Spa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Timestamp;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class SpaDAO extends DAO {
	private int lastId = 0;

	public SpaDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Spa servico) {
		boolean status = false;
		try {
			lastId++;
			String sql = "INSERT INTO spa (id, servico, preco, duracao) "
		               + "VALUES (" + lastId + ", '" + servico.getServico() + "', "
		               + servico.getPreco() + ", " + servico.getDuracao() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
//		    st.setTimestamp(1, Timestamp.valueOf(produto.getDataFabricacao()));
//			st.setDate(2, Date.valueOf(produto.getDataValidade()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Spa get(int id) {
		Spa servico = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM spa WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 servico = new Spa(rs.getInt("id"), rs.getString("servico"), (float)rs.getDouble("preco"), 
	                				   rs.getInt("duracao"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return servico;
	}
	
	
	public List<Spa> get() {
		return get("");
	}

	
	public List<Spa> getOrderById() {
		return get("id");		
	}
	
	
	public List<Spa> getOrderByServico() {
		return get("servico");		
	}
	
	
	public List<Spa> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Spa> get(String orderBy) {
		List<Spa> servicos = new ArrayList<Spa>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM spa" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Spa s = new Spa(rs.getInt("id"), rs.getString("servico"), (float)rs.getDouble("preco"), 
	        			                rs.getInt("duracao"));
	            servicos.add(s);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return servicos;
	}
	
	
	public boolean update(Spa spa) {
		boolean status = false;
		try {  
			String sql = "UPDATE spa SET servico = '" + spa.getServico() + "', "
					   + "preco = " + spa.getPreco() + ", " 
					   + "duracao = " + spa.getDuracao() 
					   + " WHERE id = " + spa.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
//		    st.setTimestamp(1, Timestamp.valueOf(produto.getDataFabricacao()));
//			st.setDate(2, Date.valueOf(produto.getDataValidade()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM spa WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			System.out.println("Falha no delete");
			throw new RuntimeException(u);
		}
		return status;
	}
}