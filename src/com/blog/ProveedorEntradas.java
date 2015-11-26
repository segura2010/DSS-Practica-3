package com.blog;

import java.util.HashMap;
import java.util.Map;

public enum ProveedorEntradas {
	
	instance;

	private Map<String, Entrada> contentProvider = new HashMap<String, Entrada>();

	private ProveedorEntradas()
	{
		Entrada e = new Entrada("Primera Entrada", "Esta es mi primera entrada del blog");
		contentProvider.put("1", e);
		
		e = new Entrada("Segunda Entrada", "Esta es mi segunda entrada");
		contentProvider.put("2", e);
	}
	
	public Map<String, Entrada> getModel()
	{
		return contentProvider;
	}
	
}
