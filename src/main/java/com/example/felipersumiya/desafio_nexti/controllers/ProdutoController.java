package com.example.felipersumiya.desafio_nexti.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.felipersumiya.desafio_nexti.domain.Produto;
import com.example.felipersumiya.desafio_nexti.services.ProdutoService;

@RestController
@RequestMapping (value = "/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
		// Listar produtos.
		@GetMapping
		public ResponseEntity<List<Produto>> listarProdutos(){
			
			List<Produto> listaProdutos = produtoService.listarProdutos();
			
			return ResponseEntity.ok().body(listaProdutos);
			
		}
		
		//Listar Produto por id.	
		@GetMapping (value = "/{id}")
		public ResponseEntity<Produto> listarPorId(@PathVariable Long id){
			
			Produto produto = produtoService.listarPorId(id);
			
			return ResponseEntity.ok().body(produto);
		}
		
		//Incluir Produtos.
		@PostMapping
		public ResponseEntity<Produto> inserirProduto (@RequestBody Produto produto){
			
			produtoService.inserirProduto(produto);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(produto.getId()).toUri();
			return ResponseEntity.created(uri).build();
			
		}
		
		//Atualizar Produto
		@PutMapping
		public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto){
			//ajustar este método e deixar certinho
			produtoService.atualizarProduto(id, produto);
			return ResponseEntity.ok().body(produto);
		}		
		

		//Excluir Produto
		@DeleteMapping
		public ResponseEntity<Void> excluirProduto(@PathVariable Long id){
			
			produtoService.excluirProduto(id);
			
			return ResponseEntity.noContent().build();
		}
		
}
