package com.blog;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


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
	String id;
	
	
	
	public EntradaRecurso(UriInfo uriInfo, Request peticion, String id)
	{
	    this.uriInfo = uriInfo;
	    this.peticion = peticion;
	    this.id = id;
	}
	
	

	// Este metodo se llamara si existe una peticion XML desde el cliente
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Entrada getXML()
	{
		Entrada ent = new Entrada();
		ent.setId("1");
		ent.setTitulo("Hola");
		ent.setContenido("Mundo");
		return ent;
	}
	
	//Lo que sigue se puede utilizar para comprobar la integracion con el navegador que utilicemos
	@GET
	@Produces({ MediaType.TEXT_XML })
	public Entrada getHTML()
	{
		Entrada ent = new Entrada();
		ent.setId("1");
		ent.setTitulo("Hola");
		ent.setContenido("Mundo");
		return ent;
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putEntrada(JAXBElement<Entrada> e)
	{
	  Entrada entrada = e.getValue();
	  return putAndGetResponse(entrada);
	}
	
	@DELETE
	public void deleteEntrada()
	{
	  Entrada c = ProveedorEntradas.INSTANCE.getModelo().remove(id);
	  if(c==null)
	    throw new RuntimeException("Delete: Entrada con " + id +  " no se ha encontrado");
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
