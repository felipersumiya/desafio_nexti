package com.example.felipersumiya.desafio_nexti.domain.dtos;

import com.example.felipersumiya.desafio_nexti.domain.Cliente;

//Classe criada para representar os dados que serão recebidos e exibidos nas requisições, de acordo com a necessidade da aplicação. 

public class ClienteDto {
	
	private Long id;
	private String nome;
	private String cpf;
	private String dataDeNascimento;
	
	public ClienteDto() {
		
		
	}
	
	public ClienteDto(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		cpf = cliente.getCpf();
		dataDeNascimento = cliente.getDataDeNascimento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	

}
