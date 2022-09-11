package app;

import static spark.Spark.*;
import service.SpaService;

public class AplicacaoSpa {
	
	private static SpaService spaService = new SpaService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/spa/insert", (request, response) -> spaService.insert(request, response));

        get("/spa/:id", (request, response) -> spaService.get(request, response));
        
        get("/spa/list/:orderby", (request, response) -> spaService.getAll(request, response));

        get("/spa/update/:id", (request, response) -> spaService.getToUpdate(request, response));
        
        post("/spa/update/:id", (request, response) -> spaService.update(request, response));
           
        get("/spa/delete/:id", (request, response) -> spaService.delete(request, response));

             
    }
	
}
