package com.example.felipersumiya.desafio_nexti.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.felipersumiya.desafio_nexti.domain.Cliente;
import com.example.felipersumiya.desafio_nexti.domain.dtos.ClienteDto;
import com.example.felipersumiya.desafio_nexti.repositories.ClienteRepository;
import com.example.felipersumiya.desafio_nexti.services.exceptions.DatabaseException;
import com.example.felipersumiya.desafio_nexti.services.exceptions.ResourceNotFoundException;



@Service
public class ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public List<Cliente> listarClientes(){
		
		return clienteRepository.findAll();
		
	}
	
	public Cliente listarPorId(Long id) {
			
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ResourceNotFoundException(id));

	}
	
	public void inserirCliente(Cliente cliente) {
		
		try {
			
			clienteRepository.save(cliente);
			
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(cliente.getId());
			
		}
		
		
	}
	
	public Cliente atualizarCliente(Long id, Cliente cliente) {
		
		try {
			
		//rever nomes destes objetos.
		Cliente clienteSalvar = clienteRepository.getById(id);
		atualizarDaddos(clienteSalvar, cliente);
		return clienteRepository.save(clienteSalvar); 
		
		}catch(DataIntegrityViolationException e) {
			
			throw new DatabaseException();
			
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
			
		}
		
	}
	
	private void atualizarDaddos(Cliente clienteSalvar, Cliente cliente) {
		
		clienteSalvar.setNome(cliente.getNome());
		clienteSalvar.setCpf(cliente.getCpf()); // pensar se deve atualizarCpf
		clienteSalvar.setDataDeNascimento(cliente.getDataDeNascimento());
		
	}

	public void excluirClientePorID(Long id) {
		
		try {
			
			clienteRepository.deleteById(id);
			
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
			
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(id);
				
		}
	
		
	}
	
	public Cliente converteDto (ClienteDto clienteDto) {
		
		 return new Cliente(clienteDto.getId(), clienteDto.getNome(),clienteDto.getCpf(), clienteDto.getDataDeNascimento());
		
	}
	

}
