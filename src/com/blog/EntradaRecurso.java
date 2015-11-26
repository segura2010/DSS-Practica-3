package com.blog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/entrada")
public class EntradaRecurso {

	// Este metodo se llamara si existe una peticion XML desde el cliente
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Entrada getXML()
	{
		Entrada ent = new Entrada("Hola", "Mundo");
		return ent;
	}
	
	//Lo que sigue se puede utilizar para comprobar la integracion con el navegador que utilicemos
	@GET
	@Produces({ MediaType.TEXT_XML })
	public Entrada getHTML()
	{
		Entrada ent = new Entrada("Hola", "Mundo");
		return ent;
	}
	
}
