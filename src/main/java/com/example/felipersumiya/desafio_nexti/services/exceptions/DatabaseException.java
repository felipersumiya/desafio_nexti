package com.example.felipersumiya.desafio_nexti.services.exceptions;


public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	
	
	public DatabaseException() {
		
		super("Erro ao persistir no banco de dados.");
	}
	
	public DatabaseException(Object id) {
		
		super("Não foi possível realizar a operação no banco de dados.");
	}
	

	
}
