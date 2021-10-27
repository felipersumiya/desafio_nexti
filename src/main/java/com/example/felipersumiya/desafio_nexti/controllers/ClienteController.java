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
import com.example.felipersumiya.desafio_nexti.domain.dtos.ClienteDto;
import com.example.felipersumiya.desafio_nexti.services.ClienteService;


@RestController
@RequestMapping ( value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	// Listar clientes.
	@GetMapping
	public ResponseEntity<List<ClienteDto>> listarClientes(){
		
		List<Cliente> listaClientes = clienteService.listarClientes();
		List<ClienteDto> listaDto = listaClientes.stream().map( x -> new ClienteDto(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaDto);
		
	}
	
	//Listar cliente por id.	
	@GetMapping (value = "/{id}")
	public ResponseEntity<ClienteDto> listarPorId(@PathVariable Long id){
		
		Cliente cliente = clienteService.listarPorId(id);
		ClienteDto clienteDto = new ClienteDto(cliente);	
		
		return ResponseEntity.ok().body(clienteDto);
	}
	
	//Incluir clientes.
	@PostMapping
	public ResponseEntity<Void> inserirCliente (@RequestBody ClienteDto clienteDto){
		
		Cliente cliente = clienteService.converteDto(clienteDto);
		clienteService.inserirCliente(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(clienteDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	//Atualizar cliente
	@PutMapping (value = "/{id}")
	public ResponseEntity<Void> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto){
		//ajustar este método e deixar certinho
		Cliente cliente = clienteService.converteDto(clienteDto);
		clienteService.atualizarCliente(id, cliente);
		return ResponseEntity.ok().build();
	}
	
	

	//Excluir cliente
	@DeleteMapping (value = "/{id}")
	public ResponseEntity<Void> excluirCliente(@PathVariable Long id){
		
		clienteService.excluirClientePorID(id);
		
		return ResponseEntity.noContent().build();
	}

}
