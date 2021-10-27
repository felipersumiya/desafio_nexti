package com.example.felipersumiya.desafio_nexti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.felipersumiya.desafio_nexti.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
}