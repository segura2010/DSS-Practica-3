package com.blog;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

@XmlRootElement
public class Entrada {

	private String id;
	private String titulo;
	private String contenido;
	
	// Se permite añadir una imagen de presentacion
	private String imagen;
	
	public Entrada() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	//@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	//@XmlAttribute
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	public String getContenido() {
		return contenido;
	}
	//@XmlAttribute
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}
