package service;

import java.util.Scanner;
//import java.time.LocalDate;
import java.io.File;
//import java.time.LocalDateTime;
import java.util.List;
import dao.SpaDAO;
import model.Spa;
import spark.Request;
import spark.Response;

public class SpaService {
	private SpaDAO spaDAO = new SpaDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_SERVICO = 2;
	private final int FORM_ORDERBY_PRECO = 3;

	public SpaService() {
		makeForm();
	}

	public void makeForm() {
		makeForm(FORM_INSERT, new Spa(), FORM_ORDERBY_SERVICO);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Spa(), orderBy);
	}

	
	public void makeForm(int tipo, Spa spa, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umServico = "";
		if(tipo != FORM_INSERT) {
			umServico += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/spa/list/1\">Novo Serviço</a></b></font></td>";
			umServico += "\t\t</tr>";
			umServico += "\t</table>";
			umServico += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/spa/";
			String name, servico, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Serviço";
				servico = "massagem, ...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + spa.getId();
				name = "Atualizar Serviço (ID " + spa.getId() + ")";
				servico = spa.getServico();
				buttonLabel = "Atualizar";
			}
			umServico += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umServico += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td>&nbsp;Descrição: <input class=\"input--register\" type=\"text\" name=\"servico\" value=\""+ servico +"\"></td>";
			umServico += "\t\t\t<td>Preco: <input class=\"input--register\" type=\"text\" name=\"preco\" value=\""+ spa.getPreco() +"\"></td>";
			umServico += "\t\t\t<td>Duração: <input class=\"input--register\" type=\"text\" name=\"duracao\" value=\""+ spa.getDuracao() +"\"></td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			//umServico += "\t\t\t<td>&nbsp;Data de fabricação: <input class=\"input--register\" type=\"text\" name=\"dataFabricacao\" value=\""+ produto.getDataFabricacao().toString() + "\"></td>";
			//umServico += "\t\t\t<td>Data de validade: <input class=\"input--register\" type=\"text\" name=\"dataValidade\" value=\""+ produto.getDataValidade().toString() + "\"></td>";
			umServico += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umServico += "\t\t</tr>";
			umServico += "\t</table>";
			umServico += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umServico += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Serviço (ID " + spa.getId() + ")</b></font></td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td>&nbsp;Serviço: "+ spa.getServico() +"</td>";
			umServico += "\t\t\t<td>Preco: "+ spa.getPreco() +"</td>";
			umServico += "\t\t\t<td>Duração: "+ spa.getDuracao() +"</td>";
			umServico += "\t\t</tr>";
			//umServico += "\t\t<tr>";
			//umServico += "\t\t\t<td>&nbsp;Data de fabricação: "+ produto.getDataFabricacao().toString() + "</td>";
			//umServico += "\t\t\t<td>Data de validade: "+ produto.getDataValidade().toString() + "</td>";
			umServico += "\t\t\t<td>&nbsp;</td>";
			umServico += "\t\t</tr>";
			umServico += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-SERVICO>", umServico);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Serviços</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/spa/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/spa/list/" + FORM_ORDERBY_SERVICO + "\"><b>Serviço</b></a></td>\n" +
        		"\t<td><a href=\"/spa/list/" + FORM_ORDERBY_PRECO + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Spa> servicos;
		if (orderBy == FORM_ORDERBY_ID) {                 	servicos = spaDAO.getOrderById();
		} else if (orderBy == FORM_ORDERBY_SERVICO) {		servicos = spaDAO.getOrderByServico();
		} else if (orderBy == FORM_ORDERBY_PRECO) {			servicos = spaDAO.getOrderByPreco();
		} else {											servicos = spaDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Spa s : servicos) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + s.getId() + "</td>\n" +
            		  "\t<td>" + s.getServico() + "</td>\n" +
            		  "\t<td>" + s.getPreco() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/spa/" + s.getId() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/spa/update/" + s.getId() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteServico('" + s.getId() + "', '" + s.getServico() + "', '" + s.getPreco() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-SERVICO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String servico = request.queryParams("servico");
		float preco = Float.parseFloat(request.queryParams("preco"));
		int duracao = Integer.parseInt(request.queryParams("duracao"));
		//LocalDateTime dataFabricacao = LocalDateTime.parse(request.queryParams("dataFabricacao"));
		//LocalDate dataValidade = LocalDate.parse(request.queryParams("dataValidade"));
		
		String resp = "";
		
		Spa spa = new Spa(-1, servico, preco, duracao);
		
		if(spaDAO.insert(spa) == true) {
            resp = "Serviço (" + servico + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "PrServiçooduto (" + servico + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Spa spa= (Spa) spaDAO.get(id);
		
		if (spa != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, spa, FORM_ORDERBY_SERVICO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Serviço " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Spa spa= (Spa) spaDAO.get(id);
		
		if (spa != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, spa, FORM_ORDERBY_SERVICO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Serviço " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Spa spa = spaDAO.get(id);
        String resp = "";       

        if (spa != null) {
        	spa.setServico(request.queryParams("servico"));
        	spa.setPreco(Float.parseFloat(request.queryParams("preco")));
        	spa.setDuracao(Integer.parseInt(request.queryParams("duracao")));
        	//produto.setDataFabricacao(LocalDateTime.parse(request.queryParams("dataFabricacao")));
        	//produto.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));
        	spaDAO.update(spa);
        	response.status(200); // success
            resp = "Serviço (ID " + spa.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Serviço (ID " + spa.getId() + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Spa spa = spaDAO.get(id);
        String resp = "";       

        if (spa != null) {
            spaDAO.delete(id);
            response.status(200); // success
            resp = "Serviço (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Serviço (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}
