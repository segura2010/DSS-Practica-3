package com.blog;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/entradas")
public class EntradasRecurso {

	
	// Esta clase devuelve la lista entera de entradas
	
	@GET
	@Produces({ MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON })
	public Entradas getXML()
	{
		Entradas es = new Entradas();
		return es;
	}
	
	
	//Lo que sigue se puede utilizar para comprobar la integracion con el navegador que utilicemos
	@GET
	@Produces({ MediaType.TEXT_XML })
	public Entradas getHTML()
	{
		Entradas es = new Entradas();
		return es;
	}
	
}
