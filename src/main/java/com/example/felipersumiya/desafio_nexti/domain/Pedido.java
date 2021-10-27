package com.example.felipersumiya.desafio_nexti.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "tb_pedido")
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	private Double totalCompra;
	private String dataCompra;
	
	@ManyToMany
	@JoinTable (name = "tb_pedido_produto", joinColumns = @JoinColumn(name = "pedido_id"), 
	inverseJoinColumns = @JoinColumn (name = "produto_id"))
	private List<Produto> produtos = new ArrayList<>();
	
	public Pedido() {
		
	}
	
	
	public Pedido(Long id, Cliente cliente, Double totalCompra, String dataCompra) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.totalCompra = totalCompra;
		this.dataCompra = dataCompra;
	
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	


}
