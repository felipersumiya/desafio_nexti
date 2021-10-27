package com.example.felipersumiya.desafio_nexti.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.felipersumiya.desafio_nexti.domain.Cliente;
import com.example.felipersumiya.desafio_nexti.repositories.ClienteRepository;

@Service
public class ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public List<Cliente> listarClientes(){
		
		return clienteRepository.findAll();
		
	}
	
	public Cliente listarPorId(Long id) {
			
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
		
	}
	
	public void inserirCliente(Cliente cliente) {
		
		clienteRepository.save(cliente);
	}
	
	public Cliente atualizarCliente(Long id, Cliente cliente) {
		
		//rever nomes destes objetos.
		Cliente clienteSalvar = clienteRepository.getById(id);
		atualizarDaddos(clienteSalvar, cliente);
		return clienteRepository.save(clienteSalvar); 
	}
	
	private void atualizarDaddos(Cliente clienteSalvar, Cliente cliente) {
		
		clienteSalvar.setNome(cliente.getNome());
		clienteSalvar.setCpf(cliente.getCpf()); // pensar se deve atualizarCpf
		clienteSalvar.setDataDeNascimento(cliente.getDataDeNascimento());
		
	}

	public void excluirCliente(Long id) {
		
		clienteRepository.deleteById(id);
		
	}
	

}
