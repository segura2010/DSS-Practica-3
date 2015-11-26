package com.blog;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Entrada {

	private String id;
	private String titulo;
	private String contenido;
	
	// Se permite añadir una imagen de presentacion
	//private imagen;
	
	public Entrada() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
}
