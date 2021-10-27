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
import com.example.felipersumiya.desafio_nexti.services.ClienteService;

@RestController
@RequestMapping ( value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	// Listar clientes.
	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes(){
		
		List<Cliente> listaClientes = clienteService.listarClientes();
		
		return ResponseEntity.ok().body(listaClientes);
		
	}
	
	//Listar cliente por id.	
	@GetMapping (value = "/{id}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable Long id){
		
		Cliente cliente = clienteService.listarPorId(id);
		
		return ResponseEntity.ok().body(cliente);
	}
	
	//Incluir clientes.
	@PostMapping
	public ResponseEntity<Cliente> inserirCliente (@RequestBody Cliente cliente){
		
		clienteService.inserirCliente(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	//Atualizar cliente
	@PutMapping
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
		//ajustar este método e deixar certinho
		clienteService.atualizarCliente(id, cliente);
		return ResponseEntity.ok().body(cliente);
	}
	
	

	//Excluir cliente
	@DeleteMapping
	public ResponseEntity<Void> excluirCliente(@PathVariable Long id){
		
		clienteService.excluirCliente(id);
		
		return ResponseEntity.noContent().build();
	}
	
	

}
