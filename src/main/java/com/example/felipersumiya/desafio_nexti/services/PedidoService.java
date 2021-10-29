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
			
			
			Pedido pedidoSalvar = pedidoRepository.getById(id);
			atualizarDados(pedidoSalvar, pedido);
			return pedidoRepository.save(pedidoSalvar); 
			
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException();
			
		}catch(EntityNotFoundException e) {
			
			throw new ResourceNotFoundException(id);
		}
	}
	
	//Atualiza os dados em pedidoSalvar com os dados de pedido.
	
	private void atualizarDados(Pedido pedidoSalvar, Pedido pedido) {
		
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
	
	//Converte o pedidoDto para o tipo Pedido.
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
	
	//Insere cliente em Pedido - teste que está na nova branch
	public void inserirClientePedido(Long id, Cliente cliente) {
		
		try {
				Pedido pedidoBd= pedidoRepository.getById(id);
				Cliente clienteBd = clienteRepository.getById(cliente.getId());
		
				//Salvar cliente em pedido
		
				if(pedidoBd.getCliente() == null) {
			
					//Adiciona os atributos nos objetos
					pedidoBd.setCliente(clienteBd);
					clienteBd.getPedidos().add(pedidoBd);
			
					//Atualiza pedido e cliente no banco de dados.
			
					pedidoRepository.save(pedidoBd);
					clienteRepository.save(clienteBd);
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
	
	//Exclui cliente de pedido.
	public void removeClientePedido(Long id, Cliente cliente) {
		

		try {
		
				Pedido pedidoBd= pedidoRepository.getById(id);
				Cliente clienteBd = clienteRepository.getById(cliente.getId());
		
		
				if(pedidoBd.getCliente().getId() == clienteBd.getId()) {
			
					pedidoBd.setCliente(null);//remove cliente do pedido.
					clienteBd.getPedidos().remove(pedidoBd);//remove o Pedido da lista de Clientes
					
					//Atualiza pedido e cliente no banco de dados
					pedidoRepository.save(pedidoBd);
					clienteRepository.save(clienteBd);
			
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
			Produto produtoBd = produtoRepository.getById(produto.getId());
			
			//obtém a lista de produtos daquele pedido
			listaProdutosBd = pedidoBd.getProdutos();
			
			if(listaProdutosBd.contains(produtoBd) == false) {
				
				pedidoBd.getProdutos().add(produtoBd);
				produtoBd.getPedidos().add(pedidoBd);
				
				//Atualiza pedido e produto no banco de dados.
				pedidoRepository.save(pedidoBd);
				produtoRepository.save(produtoBd);
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
	
	//Remove o produto do Pedido.
	public void removeProdutoPedido(Long id, Produto produto) {
		
		try {
			
	
			List<Produto> listaProdutosBd = new ArrayList<>();
			List<Pedido> listaPedidosBd = new ArrayList<>();
			
			Pedido pedidoBd = pedidoRepository.getById(id);
			Produto produtoBd = produtoRepository.getById(produto.getId());
			
			
			//Obtém a lista de produtos daquele pedido
			listaProdutosBd = pedidoBd.getProdutos();
			//Obtém a lista de pedidos do produto.
			listaPedidosBd = produtoBd.getPedidos();
			
			//Remove o produto da lista caso ele exista.
			listaProdutosBd.removeIf(produtoLista -> produtoLista.getId().equals(produtoBd.getId()));
			//Remove o pedido da lista caso ele exista.
			listaPedidosBd.removeIf(pedidoLista -> pedidoLista.getId().equals(pedidoBd.getId()));
			
			//Atualiza os dados no banco de dados.
			pedidoRepository.save(pedidoBd);
			produtoRepository.save(produtoBd);
			
			
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
