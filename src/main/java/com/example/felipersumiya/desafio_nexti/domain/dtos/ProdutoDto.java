package com.example.felipersumiya.desafio_nexti.domain.dtos;

import com.example.felipersumiya.desafio_nexti.domain.Produto;

public class ProdutoDto {
	
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private Integer quantidade;
	
	public ProdutoDto() {
		
	}
	public ProdutoDto(Produto produto) {
		id = produto.getId();
		nome = produto.getNome();
		descricao = produto.getDescricao();
		preco = produto.getPreco();
		quantidade = produto.getQuantidade();
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	

}
