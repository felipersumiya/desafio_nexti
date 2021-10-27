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
			
			//Verificar outros métodos.
			
			
			//Vincular Cliente ao Pedido
			
			@PutMapping ("/clienteI/{id}")
			public ResponseEntity<Pedido> insereClientePedido (@PathVariable Long id, @RequestBody Cliente cliente){
				
				pedidoService.inserirClientePedido(id, cliente);
				Pedido pedido = pedidoService.listarPorId(id);
				return ResponseEntity.ok().body(pedido);

			}
			
			//Remove Cliente do Pedido
			@PutMapping ("/clienteR/{id}")
			public ResponseEntity<Pedido> removeClientePedido (@PathVariable Long id, @RequestBody Cliente cliente){
				
				pedidoService.removeClientePedido(id, cliente);
				Pedido pedido = pedidoService.listarPorId(id);
				return ResponseEntity.ok().body(pedido);

			}
			
			
			
			
			/*
			 * 
			 * 
			 * 
			 * 
			 * 
			 * @PutMapping (value = "/{id}")
	public ResponseEntity<UsuarioDto> insereComicUser (@PathVariable Long id, @RequestBody ComicsDto comicDto){
	
		Comics comic = comicsService.converteDto(comicDto);
		Usuario  usuario  = new Usuario();
		usuarioComicsService.inserirComicUsuario(id, comic);
		usuario = usuarioService.findById(id);
		return ResponseEntity.ok().body(new UsuarioDto(usuario));

	}
	
			 */
			
			//Remover Clientes de Pedidos
			
			
			
			//Vincular Produtos a Pedidos
			
			//Remover Produtos de pedidos.

}
