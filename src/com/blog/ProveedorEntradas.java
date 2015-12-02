package com.blog;

import java.util.HashMap;
import java.util.Map;

public enum ProveedorEntradas {
	
	INSTANCE; // Para el singleton

	private Map<String, Entrada> contentProvider = new HashMap<String, Entrada>();

	private ProveedorEntradas()
	{
		Entrada ent = new Entrada();
		ent.setId("1");
		ent.setTitulo("Bienvenido!");
		ent.setContenido("Este blog esta basado en la Restful API de Jersey para Java. <br> Práctica realizada por <b>Luis Alberto Segura Delgado</b> para la asignatura DSS del Master de Ingeniría Informática");
		contentProvider.put("1", ent);
		
		/*
		ent = new Entrada();
		ent.setId("2");
		ent.setTitulo("Hola2");
		ent.setContenido("Mundo2");
		contentProvider.put("2", ent);
		*/
	}
	
	public Map<String, Entrada> getModelo()
	{
		return contentProvider;
	}
	
}
