package com.example.felipersumiya.desafio_nexti.domain.dtos;

import java.util.ArrayList;
import java.util.List;

import com.example.felipersumiya.desafio_nexti.domain.Cliente;
import com.example.felipersumiya.desafio_nexti.domain.Pedido;
import com.example.felipersumiya.desafio_nexti.domain.Produto;

public class PedidoDto {
	

	private Long id;
	private Cliente cliente;
	private Double totalCompra;
	private String dataCompra;
	private List<Produto> produtos = new ArrayList<>();
	
	public PedidoDto() {
		
	}
	
	public PedidoDto(Pedido pedido) {
		id = pedido.getId(); 
		cliente = pedido.getCliente();
		totalCompra = pedido.getTotalCompra();
		dataCompra = pedido.getDataCompra();
		produtos = pedido.getProdutos();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(Double totalCompra) {
		this.totalCompra = totalCompra;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}


}
