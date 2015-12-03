package com.blog;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
	
	
	// MuLTIPART para la parte multimedia
	@POST
	@Path("/nueva")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("archivo") InputStream uploadedInputStream, @FormDataParam("archivo") FormDataBodyPart fileDetail)//, @FormParam("id") String id, @FormParam("titulo") String titulo, @FormParam("contenido") String contenido)
	{
		System.out.println("LLEGA");
		//System.out.println(titulo);
		
		String uploadedFileLocation = "./" + fileDetail.getName();

		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();

	}
	

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
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
