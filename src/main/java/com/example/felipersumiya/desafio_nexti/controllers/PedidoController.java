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

import com.example.felipersumiya.desafio_nexti.domain.Cliente;
import com.example.felipersumiya.desafio_nexti.domain.Pedido;
import com.example.felipersumiya.desafio_nexti.domain.Produto;
import com.example.felipersumiya.desafio_nexti.services.ClienteService;
import com.example.felipersumiya.desafio_nexti.services.PedidoService;


@RestController
@RequestMapping (value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ClienteService clienteService;
	
			// Listar pedidos.
			@GetMapping
			public ResponseEntity<List<Pedido>> listarpedidos(){
				
				List<Pedido> listaPedidos = pedidoService.listarPedidos();
				
				return ResponseEntity.ok().body(listaPedidos);
				
			}
			
			//Listar pedido por id.	
			@GetMapping (value = "/{id}")
			public ResponseEntity<Pedido> listarPorId(@PathVariable Long id){
				
				Pedido pedido = pedidoService.listarPorId(id);
				
				return ResponseEntity.ok().body(pedido);
			}
			
			//Incluir pedidos.
			@PostMapping
			public ResponseEntity<Pedido> inserirpedido (@RequestBody Pedido pedido){
				
				pedidoService.inserirPedido(pedido);
				
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(pedido.getId()).toUri();
				return ResponseEntity.created(uri).body(pedido);
				
			}
			
			//Atualizar pedido
			@PutMapping (value = "/{id}")
			public ResponseEntity<Pedido> atualizarpedido(@PathVariable Long id, @RequestBody Pedido pedido){
				//ajustar este método e deixar certinho
				pedidoService.atualizarPedido(id, pedido);
				return ResponseEntity.ok().body(pedido);
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
			public ResponseEntity<Pedido> insereClientePedido (@PathVariable Long id, @RequestBody Cliente cliente){
				
				pedidoService.inserirClientePedido(id, cliente);
				Pedido pedido = pedidoService.listarPorId(id);
				
				return ResponseEntity.ok().build();

			}
			
			//Remove Cliente do Pedido
			@PutMapping ("/clienteR/{id}")
			public ResponseEntity<Pedido> removeClientePedido (@PathVariable Long id, @RequestBody Cliente cliente){
				
				pedidoService.removeClientePedido(id, cliente);
				Pedido pedido = pedidoService.listarPorId(id);
				return ResponseEntity.ok().build();

			}
			
			
			/* Gerenciamento de Produtos em Pedidos**********************************************/
			
			@PutMapping ("/produtoI/{id}")
			public ResponseEntity<Pedido> insereProdutoPedido (@PathVariable Long id, @RequestBody Produto produto){
				
				pedidoService.insereProdutoPedido(id, produto);
				Pedido pedido = pedidoService.listarPorId(id);
				return ResponseEntity.ok().build();

			}
			
			@PutMapping ("/produtoR/{id}")
			public ResponseEntity<Pedido> removeProdutoPedido (@PathVariable Long id, @RequestBody Produto produto){
				
				pedidoService.removeProdutoPedido(id, produto);
				Pedido pedido = pedidoService.listarPorId(id);
				return ResponseEntity.ok().build();

			}
			
			
		

}
