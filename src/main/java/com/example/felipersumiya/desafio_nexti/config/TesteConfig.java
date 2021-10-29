package com.example.felipersumiya.desafio_nexti.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.felipersumiya.desafio_nexti.domain.Cliente;
import com.example.felipersumiya.desafio_nexti.domain.Pedido;
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
	
		//Adiciona clientes.
		
		Cliente c1 = new Cliente(null, "Felipe Sumiya", "300.200.578-50", "29/01/1989");
		Cliente c2 = new Cliente(null, "Bruno Fernando", "239.265.578-20", "20/02/1983");
		Cliente c3 = new Cliente(null, "Leandro Vieria", "379.265.578-10", "29/01/1985");
		Cliente c4 = new Cliente(null, "Bruna Soares", "234.264.578-00", "29/01/1999");

		clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		//Adiciona produtos.
		Produto p1 = new Produto(null, "iPhone 13 Pro","smartPhone", 10000.0, 3);
		Produto p2 = new Produto(null, "MacBook Pro","computador", 12000.0, 1);
		Produto p3 = new Produto(null, "Magic Mouse","periférico", 300.0, 1);
		Produto p4 = new Produto(null, "Airpods","periférico", 2000.00, 1);
		Produto p5 = new Produto(null, "Magsafe","acessório ", 350.0, 1);

		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		//Adiciona pedidos.
		Pedido pd1 = new Pedido(null, null, 100.00, "20/01/1989");
		Pedido pd2 = new Pedido(null, null, 200.00, "22/01/1989");
		Pedido pd3 = new Pedido(null, null, 300.00, "26/01/1989");
		
		//Vincula produtos e pedidos.
		pd1.getProdutos().add(p1);	
		p1.getPedidos().add(pd1);
		p1.getPedidos().add(pd2);
		pd1.getProdutos().add(p2);
		p2.getPedidos().add(pd1);
		
		pd1.getProdutos().add(p3);
		p3.getPedidos().add(pd1);
		
		pd2.getProdutos().add(p1);
		pd2.getProdutos().add(p5);
		p5.getPedidos().add(pd2);
		pd2.getProdutos().add(p4);
		p4.getPedidos().add(pd2);
		
		pd3.getProdutos().add(p3);
		p3.getPedidos().add(pd3);
		pd3.getProdutos().add(p5);
		p5.getPedidos().add(pd3);
		
		//Vincula pedidos em clientes.
		c1.getPedidos().add(pd1);
		c2.getPedidos().add(pd3);
		
		//Vincula clientes em pedidos.
		pd1.setCliente(c1);
		pd3.setCliente(c2);
		
		//Atualiza novamente os objetos no banco de dados.
		
		clienteRepository.saveAll(Arrays.asList(c1,c2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));	
		pedidoRepository.saveAll(Arrays.asList(pd1,pd2,pd3));
		

	}

}
