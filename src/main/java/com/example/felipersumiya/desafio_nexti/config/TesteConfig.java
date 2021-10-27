package com.example.felipersumiya.desafio_nexti.config;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.felipersumiya.desafio_nexti.domain.Cliente;
import com.example.felipersumiya.desafio_nexti.domain.Produto;
import com.example.felipersumiya.desafio_nexti.repositories.ClienteRepository;
import com.example.felipersumiya.desafio_nexti.repositories.PedidoRepository;
import com.example.felipersumiya.desafio_nexti.repositories.ProdutoRepository;



@Configuration
@Profile ("test")
public class TesteConfig implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
	
		
		Cliente c1 = new Cliente(null, "Felipe Sumiya", "379.264.578-50", "29/01/1989");
		Cliente c2 = new Cliente(null, "Bruno Fernando", "239.265.578-20", "20/02/1983");
		Cliente c3 = new Cliente(null, "Leandro Vieria", "379.265.578-10", "29/01/1985");
		Cliente c4 = new Cliente(null, "Bruna Soares", "234.264.578-00", "29/01/1999");
		
		
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		Produto p1 = new Produto(null, "iPhone","smartPhone", 4500.90, 1);
		Produto p2 = new Produto(null, "MacBook","computador", 9500.00, 1);
		Produto p3 = new Produto(null, "Mouse","periférico", 100.00, 1);
		Produto p4 = new Produto(null, "Fone","periférico", 1000.00, 1);
		Produto p5 = new Produto(null, "Carregador","acessório smartphone", 60.0, 1);

		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
	}

}
