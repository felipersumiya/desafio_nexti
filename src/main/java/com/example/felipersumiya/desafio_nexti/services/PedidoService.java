package com.example.felipersumiya.desafio_nexti.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.example.felipersumiya.desafio_nexti.domain.Cliente;
import com.example.felipersumiya.desafio_nexti.domain.Pedido;
import com.example.felipersumiya.desafio_nexti.domain.Produto;
import com.example.felipersumiya.desafio_nexti.domain.dtos.PedidoDto;
import com.example.felipersumiya.desafio_nexti.repositories.ClienteRepository;
import com.example.felipersumiya.desafio_nexti.repositories.PedidoRepository;
import com.example.felipersumiya.desafio_nexti.repositories.ProdutoRepository;
import com.example.felipersumiya.desafio_nexti.services.exceptions.DatabaseException;
import com.example.felipersumiya.desafio_nexti.services.exceptions.ResourceNotFoundException;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	

	public List<Pedido> listarPedidos(){
		
		return pedidoRepository.findAll();
		
		
	}
	
	public Pedido listarPorId(Long id) {
			
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ResourceNotFoundException(id));
	
		
	}
	
	public void inserirPedido(Pedido pedido) {
		
		try {
			
			pedidoRepository.save(pedido);
		
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(pedido.getId());
		}
	}
	
	public Pedido atualizarPedido(Long id, Pedido pedido) {
		
		
		try {
			
			//rever nomes destes objetos.
			Pedido pedidoSalvar = pedidoRepository.getById(id);
			atualizarDados(pedidoSalvar, pedido);
			return pedidoRepository.save(pedidoSalvar); 
			
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException();
			
		}catch(EntityNotFoundException e) {
			
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void atualizarDados(Pedido pedidoSalvar, Pedido pedido) {
		
		//Ver quais dados fazem sentido serem atualizados.
		pedidoSalvar.setCliente(pedido.getCliente());
		pedidoSalvar.setDataCompra(pedido.getDataCompra());
		pedidoSalvar.setTotalCompra(pedido.getTotalCompra());
		
	}

	public void excluirPedido(Long id) {
		
		try {
			
			pedidoRepository.deleteById(id);
			
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(id);
			
		}catch (EmptyResultDataAccessException e) {
			
			throw new ResourceNotFoundException(id);
			
		}
		
	}
	
	public Pedido converteDto (PedidoDto pedidoDto) {
		
		 return new Pedido(pedidoDto.getId(), pedidoDto.getCliente(), pedidoDto.getTotalCompra(), pedidoDto.getDataCompra());
		
	}
	
	//Realiza o cálculo do valor total do pedido.
	public static Double calcularTotalCompra(List<Produto> produtos) {
		
		Double total= 0.0;
		
		
		if(produtos.isEmpty()) {
			
			return total;
		}
		
		else {
		
			for(Produto produto : produtos) {
			
				total += (produto.getPreco() * produto.getQuantidade());
			
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
		
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(id);
			
		}catch (EntityNotFoundException e) {
			
			throw new ResourceNotFoundException(id);
			
		}catch (IllegalArgumentException e) {
			
			throw new DatabaseException(id);
			
		}catch (InvalidDataAccessApiUsageException e) {
			
			throw new DatabaseException(id);
			
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
				
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(id);
			
		}catch (EntityNotFoundException e) {
			
			throw new ResourceNotFoundException(id);
			
		}catch (NullPointerException e) {
			
			throw new ResourceNotFoundException("Este pedido não possui cliente");
			
		}catch (IllegalArgumentException e) {
			
			throw new DatabaseException(id);
			
		}catch (InvalidDataAccessApiUsageException e) {
			
			throw new DatabaseException(id);
			
		}
		
	}
	//Insere o produto em Pedido.
	public void insereProdutoPedido(Long id, Produto produto) {
		
		try {
			
			List<Produto> listaProdutosBd = new ArrayList<>();
			Pedido pedidoBd = pedidoRepository.getById(id);
			Produto produtoObj = produtoRepository.getById(produto.getId());
			
			//obtém a lista de produtos daquele pedido
			listaProdutosBd = pedidoBd.getProdutos();
			
			if(listaProdutosBd.contains(produtoObj) == false) {
				
				pedidoBd.getProdutos().add(produtoObj);
				produtoObj.getPedidos().add(pedidoBd);
				pedidoRepository.save(pedidoBd);
				produtoRepository.save(produto);
			}
			
				
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(id);
			
		}catch (EntityNotFoundException e) {
			
			throw new ResourceNotFoundException(id);
			
		}catch (IllegalArgumentException e) {
			
			throw new DatabaseException(id);
			
		}catch (InvalidDataAccessApiUsageException e) {
			
			throw new DatabaseException(id);
			
		}
				
	}
	
	//Remove o produto do Pedido
	public void removeProdutoPedido(Long id, Produto produto) {
		
		try {
			
	
			List<Produto> listaProdutosBd = new ArrayList<>();
			List<Pedido> listaPedidosBd = new ArrayList<>();
			
			Pedido pedidoBd = pedidoRepository.getById(id);
			Produto produtoObj = produtoRepository.getById(produto.getId());
			
			
			//obtém a lista de produtos daquele pedido
			listaProdutosBd = pedidoBd.getProdutos();
			listaPedidosBd = produtoObj.getPedidos();
			
		
			listaProdutosBd.removeIf(produtoLista -> produtoLista.getId().equals(produtoObj.getId()));
			listaPedidosBd.removeIf(pedidoLista -> pedidoLista.getId().equals(pedidoBd.getId()));
			
			pedidoRepository.save(pedidoBd);
			produtoRepository.save(produto);
			
			
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(id);
			
		}catch (EntityNotFoundException e) {
			
			throw new ResourceNotFoundException(id);
			
		}catch (NullPointerException e) {
			
			throw new ResourceNotFoundException("Este pedido não possui produto");
			
		}catch (IllegalArgumentException e) {
			
			throw new DatabaseException(id);
			
		}catch (InvalidDataAccessApiUsageException e) {
			
			throw new DatabaseException(id);
			
		}
				
	}
	
}
