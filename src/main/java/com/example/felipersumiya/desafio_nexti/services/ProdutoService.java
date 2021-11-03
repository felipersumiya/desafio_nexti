package com.example.felipersumiya.desafio_nexti.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.example.felipersumiya.desafio_nexti.domain.Produto;
import com.example.felipersumiya.desafio_nexti.domain.dtos.ProdutoDto;
import com.example.felipersumiya.desafio_nexti.repositories.ProdutoRepository;
import com.example.felipersumiya.desafio_nexti.services.exceptions.DatabaseException;
import com.example.felipersumiya.desafio_nexti.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public List<Produto> listarProdutos(){
		
		return produtoRepository.findAll();
		
	}
	
	public Produto listarPorId(Long id) {
			
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ResourceNotFoundException(id));
		
		
	}
	
	public void inserirProduto(Produto produto) {
		
		try {
			
			produtoRepository.save(produto);
		
		}catch(DataIntegrityViolationException e) {
			
			throw new DatabaseException(produto.getId());
		}
	}
	

	public Produto atualizarProduto(Long id, Produto produto) {
		
		
		try {
		
			Produto produtoSalvar = produtoRepository.getById(id);
			atualizarDados(produtoSalvar, produto);
			return produtoRepository.save(produtoSalvar); 
			
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException();
			
		}catch(EntityNotFoundException e) {
			
			throw new ResourceNotFoundException(id);
		}
	}
	
	//Salva os atributos de produto em produtoSalvar para atualizar no banco de dados.
	private void atualizarDados(Produto produtoSalvar, Produto produto) {
		
		produtoSalvar.setNome(produto.getNome());
		produtoSalvar.setDescricao(produto.getDescricao());
		produtoSalvar.setPreco(produto.getPreco());
		produtoSalvar.setQuantidade(produto.getQuantidade());
		
	}

	public void excluirProduto(Long id) {
		
		try {
			
			produtoRepository.deleteById(id);
			
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(id);
			
		}catch (EmptyResultDataAccessException e) {
			
			throw new ResourceNotFoundException(id);
			
		}
		
	}
	
	//Converte o produtoDto para o tipo Produto.
	public Produto converteDto(ProdutoDto produtoDto) {
		
		 return new Produto(produtoDto.getId(), produtoDto.getNome(), produtoDto.getDescricao(), produtoDto.getPreco(), produtoDto.getQuantidade());
		
	}
	
}
