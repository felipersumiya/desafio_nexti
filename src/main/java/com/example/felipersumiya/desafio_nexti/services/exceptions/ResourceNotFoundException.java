package com.example.felipersumiya.desafio_nexti.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super ("Não foi possível inserir");
		
	}
	public ResourceNotFoundException (Object id) {
		super("Recurso não encontrado. Id:" + id);
	}
	
	

}
