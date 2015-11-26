package com.blog;

public class Entrada {

	private String titulo;
	private String contenido;
	
	// Se permite añadir una imagen de presentacion
	//private imagen;
	
	public Entrada(String titulo, String contenido) {
		// TODO Auto-generated constructor stub
		this.titulo = titulo;
		this.contenido = contenido;
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
