package com.blog;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Entradas {

	private Map<String, Entrada> entradas = new HashMap<String, Entrada>();
	
	public Entradas() {
		// TODO Auto-generated constructor stub
		entradas = ProveedorEntradas.INSTANCE.getModelo();
	}
	
	public Map<String, Entrada> getEntradas() {
		entradas = ProveedorEntradas.INSTANCE.getModelo();
		return entradas;
	}
	public void setEntradas(Map<String, Entrada> entradas) {
		this.entradas = entradas;
	}
	
}
