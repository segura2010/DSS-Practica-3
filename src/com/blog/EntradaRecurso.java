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
import java.net.URI;
import java.net.URISyntaxException;

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
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


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
	

	// Obtener una entrada en XML y JSON
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
	
	// Obtener una entrada en plano
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
	
	// Crear nueva entrada (por PUT y sin foto subida, no se usa)
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Entrada putEntrada(JAXBElement<Entrada> e)
	{
		Entrada entrada = e.getValue();
		ProveedorEntradas.INSTANCE.getModelo().put(entrada.getId(), entrada);
		return entrada;
	}
	
	// Borrado de entradas
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
	public Response uploadFile(@FormDataParam("archivo") InputStream uploadedInputStream, @FormDataParam("archivo") FormDataContentDisposition fileDetail, @FormDataParam("id") String id, @FormDataParam("titulo") String titulo, @FormDataParam("contenido") String contenido) throws IOException
	{
		String uploadedFileLocation = fileDetail.getFileName();

		// guardar..
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String current = new java.io.File( "." ).getCanonicalPath(); // obtenemos el path actual
		String localizacion = current + "/" + uploadedFileLocation; // preparamos la localizacion del fichero para guardarla y despues saber donde esta
		String output = "Archivo subido a: " + localizacion + "<br> Volviendo a la página de inicio..";
		String redirect = "<script> setTimeout(function(){ document.location.href='http://localhost:8080/Practica3_LuisAlbertoSeguraDelgado/'; }, 2000); </script>";

		Entrada entrada = new Entrada();
		entrada.setContenido(contenido);
		entrada.setId(id);
		entrada.setTitulo(titulo);
		entrada.setImagen(localizacion);
		ProveedorEntradas.INSTANCE.getModelo().put(id, entrada);
		
		return Response.status(200).entity(output + redirect).build();

	}
	
	// Devuelve la foto de una entrada
	@GET
	@Path("/imagen")
	@Produces({ MediaType.TEXT_XML })
	public Response getImagen(@QueryParam("entrada") String entrada) throws URISyntaxException
	{
		if(ProveedorEntradas.INSTANCE.getModelo().containsKey(entrada))
		{
			Entrada ent = ProveedorEntradas.INSTANCE.getModelo().get(entrada);
			if(ent.getImagen() != null)
			{
				if(ent.getImagen().startsWith("http"))
				{
					return Response.seeOther( new URI(ent.getImagen()) ).build();
				}
				else
				{
					File imagenF = new File(ent.getImagen());
					return Response.ok(imagenF, "application/zip").build();
				}
			}
			return null;
		}
		else
		{
			return null;
		}
	}
	

	// Guarda la foto en local
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
	
}
