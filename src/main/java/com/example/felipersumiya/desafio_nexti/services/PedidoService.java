package com.example.felipersumiya.desafio_nexti.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.felipersumiya.desafio_nexti.domain.Cliente;
import com.example.felipersumiya.desafio_nexti.domain.Pedido;
import com.example.felipersumiya.desafio_nexti.domain.Produto;
import com.example.felipersumiya.desafio_nexti.repositories.ClienteRepository;
import com.example.felipersumiya.desafio_nexti.repositories.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	

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
	
	//Realiza o cálculo do valor total do pedido.
	public static Double calcularTotalCompra(List<Produto> produtos) {
		
		Double total= 0.0;
		
		
		if(produtos.isEmpty()) {
			
			return total;
		}
		
		else {
		
			for(Produto x : produtos) {
			
				total += (x.getPreco() * x.getQuantidade());
			
			}
		}
	
		return total;
		
	}
	
	//Insere cliente em Pedido
	public void inserirClientePedido(Long id, Cliente cliente) {
		
		try {
				Pedido pedidoBd= pedidoRepository.getById(id);
				Cliente clienteObj = clienteRepository.getById(cliente.getId());
		
				//Salvar cliente em pedido
		
				if(pedidoBd.getCliente() == null) {
			
					//Adiciona os atributos nos objetos
					pedidoBd.setCliente(cliente);
					clienteObj.getPedidos().add(pedidoBd);
			
					// Persiste os objetos no repositório
			
					pedidoRepository.save(pedidoBd);
					clienteRepository.save(cliente);
				}
		
		}catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	//Exclui cliente de pedido
	public void removeClientePedido(Long id, Cliente cliente) {
		

		try {
		
				Pedido pedidoBd= pedidoRepository.getById(id);
				Cliente clienteObj = clienteRepository.getById(cliente.getId());
		
		
				if(pedidoBd.getCliente().getId() == clienteObj.getId()) {
			
					pedidoBd.setCliente(null);//remove cliente
					clienteObj.getPedidos().remove(pedidoBd);//remove o Pedido da lista de Clientes
					pedidoRepository.save(pedidoBd);
					clienteRepository.save(clienteObj);
			
				}
				
		}catch (RuntimeException e) {
			
			e.printStackTrace();
		}
		/*
		if(pedidoBd.getCliente() == null) {
			
			System.out.println("O pedido não possui cliente vinculado");
		}
		
		if (pedidoBd.getCliente().getId() != clienteObj.getId()) {
			
			System.out.println("O cliente enviado na requisição não corresponde ao cliente vinculado ao pedido");
			
		}
		
		//Se o id do Cliente for != do id do cliente enviado na requisição, quer dizer que o cliente não pode ser excluído,
		// ou por que o cliente que o pedido possui é outro, OU porque o Pedido não possui cliente vinculado
		 */
		
	}
	

}
