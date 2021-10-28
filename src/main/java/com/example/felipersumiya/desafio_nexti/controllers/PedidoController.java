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

import com.example.felipersumiya.desafio_nexti.domain.Cliente;
import com.example.felipersumiya.desafio_nexti.domain.Pedido;
import com.example.felipersumiya.desafio_nexti.domain.Produto;
import com.example.felipersumiya.desafio_nexti.domain.dtos.ClienteDto;
import com.example.felipersumiya.desafio_nexti.domain.dtos.PedidoDto;
import com.example.felipersumiya.desafio_nexti.domain.dtos.ProdutoDto;
import com.example.felipersumiya.desafio_nexti.services.ClienteService;
import com.example.felipersumiya.desafio_nexti.services.PedidoService;
import com.example.felipersumiya.desafio_nexti.services.ProdutoService;


@RestController
@RequestMapping (value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
			// Listar pedidos.
			@GetMapping
			public ResponseEntity<List<PedidoDto>> listarpedidos(){
				
				List<Pedido> listaPedidos = pedidoService.listarPedidos();
				List<PedidoDto> listaPedidosDtos = listaPedidos.stream().map( pedido -> new PedidoDto(pedido)).collect(Collectors.toList());
				
				
				return ResponseEntity.ok().body(listaPedidosDtos);
				
			}
			
			//Listar pedido por id.	
			@GetMapping (value = "/{id}")
			public ResponseEntity<PedidoDto> listarPorId(@PathVariable Long id){
				
				Pedido pedido = pedidoService.listarPorId(id);
				PedidoDto pedidoDto = new PedidoDto(pedido);
				
	
				return ResponseEntity.ok().body(pedidoDto);
			}
			
			//Incluir pedidos.
			@PostMapping
			public ResponseEntity<Void> inserirpedido (@RequestBody PedidoDto pedidoDto){
				
				Pedido pedido = pedidoService.converteDto(pedidoDto);
				pedidoService.inserirPedido(pedido);
				
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(pedidoDto.getId()).toUri();
				return ResponseEntity.created(uri).build();
				
			}
			
			//Atualizar pedido
			@PutMapping (value = "/{id}")
			public ResponseEntity<Void> atualizarpedido(@PathVariable Long id, @RequestBody PedidoDto pedidoDto){
				
				//ajustar este método e deixar certinho
				
				Pedido pedido = pedidoService.converteDto(pedidoDto);
				pedidoService.atualizarPedido(id, pedido);
				return ResponseEntity.ok().build();
				
			}		
			

			//Excluir pedido
			@DeleteMapping (value = "/{id}")
			public ResponseEntity<Void> excluirPedido(@PathVariable Long id){
				
				pedidoService.excluirPedido(id);
				
				return ResponseEntity.noContent().build();
			}
			
			/* Gerenciamento de Clientes em Pedidos**********************************************/
			
			//Vincular Cliente ao Pedido
			
			@PutMapping ("/clienteI/{id}")
			public ResponseEntity<Void> insereClientePedido (@PathVariable Long id, @RequestBody ClienteDto clienteDto){
				
				Cliente cliente = clienteService.converteDto(clienteDto);
				pedidoService.inserirClientePedido(id, cliente);
			
				
				return ResponseEntity.ok().build();

			}
			
			//Remove Cliente do Pedido
			@PutMapping ("/clienteR/{id}")
			public ResponseEntity<Void> removeClientePedido (@PathVariable Long id, @RequestBody ClienteDto clienteDto){
				
				Cliente cliente = clienteService.converteDto(clienteDto);
				pedidoService.removeClientePedido(id, cliente);
			
				return ResponseEntity.ok().build();

			}
			
			
			/* Gerenciamento de Produtos em Pedidos**********************************************/
			
			@PutMapping ("/produtoI/{id}")
			public ResponseEntity<Void> insereProdutoPedido (@PathVariable Long id, @RequestBody ProdutoDto produtoDto){
				
				Produto produto = produtoService.converteDto(produtoDto);
				pedidoService.insereProdutoPedido(id, produto);
				
			
				return ResponseEntity.ok().build();

			}
			
			@PutMapping ("/produtoR/{id}")
			public ResponseEntity<Void> removeProdutoPedido (@PathVariable Long id, @RequestBody ProdutoDto produtoDto){
				
				Produto produto = produtoService.converteDto(produtoDto);
				pedidoService.removeProdutoPedido(id, produto);
				
				return ResponseEntity.ok().build();

			}
			
		
		
}
