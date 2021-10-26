package com.example.felipersumiya.desafio_nexti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.felipersumiya.desafio_nexti.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
