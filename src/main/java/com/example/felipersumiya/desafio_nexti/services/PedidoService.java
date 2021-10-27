package com.example.felipersumiya.desafio_nexti.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.felipersumiya.desafio_nexti.domain.Pedido;
import com.example.felipersumiya.desafio_nexti.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	

	public List<Pedido> listarPedidos(){
		
		return pedidoRepository.findAll();
		
	}
	
	public Pedido listarPorId(Long id) {
			
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.get();
		
	}
	
	public void inserirPedido(Pedido pedido) {
		
		pedidoRepository.save(pedido);
	}
	
	public Pedido atualizarPedido(Long id, Pedido pedido) {
		
		//rever nomes destes objetos.
		Pedido pedidoSalvar = pedidoRepository.getById(id);
		atualizarDados(pedidoSalvar, pedido);
		return pedidoRepository.save(pedidoSalvar); 
	}
	
	private void atualizarDados(Pedido pedidoSalvar, Pedido pedido) {
		
		//Ver quais dados fazem sentido serem atualizados.
		pedidoSalvar.setCliente(pedido.getCliente());
		pedidoSalvar.setDataCompra(pedido.getDataCompra());
		pedidoSalvar.setTotalCompra(pedido.getTotalCompra());
		
	}

	public void excluirPedido(Long id) {
		
		pedidoRepository.deleteById(id);
		
	}


}
