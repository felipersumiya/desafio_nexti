package com.example.felipersumiya.desafio_nexti.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.felipersumiya.desafio_nexti.domain.Produto;
import com.example.felipersumiya.desafio_nexti.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public List<Produto> listarProdutos(){
		
		return produtoRepository.findAll();
		
	}
	
	public Produto listarPorId(Long id) {
			
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.get();
		
	}
	
	public void inserirProduto(Produto produto) {
		
		produtoRepository.save(produto);
	}
	
	public Produto atualizarProduto(Long id, Produto produto) {
		
		//rever nomes destes objetos.
		Produto produtoSalvar = produtoRepository.getById(id);
		atualizarDados(produtoSalvar, produto);
		return produtoRepository.save(produtoSalvar); 
	}
	
	private void atualizarDados(Produto produtoSalvar, Produto produto) {
		
		produtoSalvar.setNome(produto.getNome());
		produtoSalvar.setDescricao(produto.getDescricao());
		produtoSalvar.setPreco(produto.getPreco());
		produtoSalvar.setQuantidade(produto.getQuantidade());
		
	}

	public void excluirProduto(Long id) {
		
		produtoRepository.deleteById(id);
		
	}
	
}
