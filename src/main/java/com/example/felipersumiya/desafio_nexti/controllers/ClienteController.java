package com.example.felipersumiya.desafio_nexti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.felipersumiya.desafio_nexti.domain.Cliente;
import com.example.felipersumiya.desafio_nexti.services.ClienteService;

@RestController
@RequestMapping ( value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	public ResponseEntity<List<Cliente>> listarClientes(){
		
		List<Cliente> listaClientes = clienteService.listarClientes();
		
		return ResponseEntity.ok().body(listaClientes);
		
	}
	

}
