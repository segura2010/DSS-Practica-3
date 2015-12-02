package com.blog;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;



@Path("/entrada")
public class EntradaRecurso {
	
	
	@Context
	UriInfo uriInfo;
	@Context
	Request peticion;
	//String id;
	
	
	/*
	public EntradaRecurso(UriInfo uriInfo, Request peticion, String id)
	{
	    this.uriInfo = uriInfo;
	    this.peticion = peticion;
	    this.id = id;
	}
	*/
	

	// Este metodo se llamara si existe una peticion XML desde el cliente
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Entrada getXML(@QueryParam("entrada") String entrada)
	{
		if(ProveedorEntradas.INSTANCE.getModelo().containsKey(entrada))
		{
			Entrada ent = ProveedorEntradas.INSTANCE.getModelo().get(entrada);
			return ent;
		}
		else
		{
			return null;
		}
	}
	
	//Lo que sigue se puede utilizar para comprobar la integracion con el navegador que utilicemos
	@GET
	@Produces({ MediaType.TEXT_XML })
	public Entrada getHTML(@QueryParam("entrada") String entrada)
	{
		if(ProveedorEntradas.INSTANCE.getModelo().containsKey(entrada))
		{
			Entrada ent = ProveedorEntradas.INSTANCE.getModelo().get(entrada);
			return ent;
		}
		else
		{
			return null;
		}
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Entrada putEntrada(JAXBElement<Entrada> e)
	{
		Entrada entrada = e.getValue();
		ProveedorEntradas.INSTANCE.getModelo().put(entrada.getId(), entrada);
		return entrada;
	}
	
	@DELETE
	public Entrada deleteEntrada(@QueryParam("entrada") String entrada)
	{
		Entrada ent = null;
		if(ProveedorEntradas.INSTANCE.getModelo().containsKey(entrada))
		{
			ent = ProveedorEntradas.INSTANCE.getModelo().remove(entrada);
			return ent;
		}
		else
		{
			return null;
		}
	}
	
	private Response putAndGetResponse(Entrada e)
	{
	    Response res;
	    if(ProveedorEntradas.INSTANCE.getModelo().containsKey(e.getId()))
	    {
	      res = Response.noContent().build();
	    }
	    else
	    {
	      res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
	    ProveedorEntradas.INSTANCE.getModelo().put(e.getId(), e);
	    return res;
	}
	
	
}
