package com.example.felipersumiya.desafio_nexti.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import com.example.felipersumiya.desafio_nexti.domain.dtos.ClienteDto;
import com.example.felipersumiya.desafio_nexti.domain.dtos.ProdutoDto;
import com.example.felipersumiya.desafio_nexti.services.ProdutoService;

@RestController
@RequestMapping (value = "/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
		// Listar produtos.
		@GetMapping
		public ResponseEntity<List<ProdutoDto>> listarProdutos(){
			
			
			List<Produto> listaProdutos = produtoService.listarProdutos();
			List<ProdutoDto> listaProdutosDto = listaProdutos.stream().map( x -> new ProdutoDto(x)).collect(Collectors.toList());
			
			return ResponseEntity.ok().body(listaProdutosDto);
			
		}
		
		//Listar Produto por id.	
		@GetMapping (value = "/{id}")
		public ResponseEntity<ProdutoDto> listarPorId(@PathVariable Long id){
			
			Produto produto = produtoService.listarPorId(id);
			ProdutoDto produtoDto = new ProdutoDto(produto);
			
			return ResponseEntity.ok().body(produtoDto);
		}
		
		//Incluir Produtos.
		@PostMapping
		public ResponseEntity<Void> inserirProduto (@RequestBody ProdutoDto produtoDto){
			
			Produto produto = produtoService.converteDto(produtoDto);
			produtoService.inserirProduto(produto);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(produtoDto.getId()).toUri();
			return ResponseEntity.created(uri).build();
			
		}
		
		//Atualizar Produto
		@PutMapping (value = "/{id}")
		public ResponseEntity<Void> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto){
			//ajustar este método e deixar certinho
			
			Produto produto = produtoService.converteDto(produtoDto);
			produtoService.atualizarProduto(id, produto);
			return ResponseEntity.ok().build();
		}		
		

		//Excluir Produto
		@DeleteMapping (value = "/{id}")
		public ResponseEntity<Void> excluirProduto(@PathVariable Long id){
			
			produtoService.excluirProduto(id);
			
			return ResponseEntity.noContent().build();
		}
		
}
