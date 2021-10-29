# Desafio Nexti - Aplicação de gerenciamento de pedidos


# Sobre o projeto

Este projeto tem como objetivo o desenvolvimento de uma API de gerenciamento de pedidos, clientes e produtos. Este desafio foi proposto pela empresa Nexti. Foi utilizado o framework Spring Boot para o desenvolvimento do back-end no padrão REST.

# Requisitos Funcionais

Basicamente as operações necessárias para atender a API de gerenciamento de pedidos são:

- Clientes: cadastrar, alterar, listar e excluir.

- Produtos: cadastrar, alterar, listar e excluir.

- Pedidos: cadastrar, alterar, listar e excluir.

- Pedidos e clientes: vincular e desvincular.

- Pedidos e produtos: vincular e desvincular.


# Requisitos e ferramentas
## Back-end

* [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
* [Maven](http://maven.apache.org/install.html)
* [PostgreSQL](https://www.postgresql.org/download/windows/)

## Frameworks utilizados
- Spring Boot 2.5.6
- JPA / Hibernate

## Ferramentas complementares

* [GIT](https://git-scm.com/download/win)
* [Postman](https://www.postman.com/downloads/)
* [Spring Tools Suite - IDE](https://spring.io/tools)

# Iniciar a aplicação
## Back end

```bash
cmd

# clonar repositório do Git Hub
git clone https://github.com/felipersumiya/desafio_nexti

# iniciar a aplicação
mvn spring-boot:run
```

Após a execução, verifique o arquivo de configuração abaixo que contém as configurações do banco de dados PostgreSQL.


``desafio_nexti\desafio_nexti\src\main\resources\application-dev.properties ``

O arquivo acima possui as configurações para o banco de dados; como URL de conexão, usuário do banco e senha. Todas essas configurações já estão realizadas, mas podem ser alteradas conforme a necessidade.
Obs: O banco de dados PostgreSQL já deve estar instalado e pronto para uso.

Para que a aplicação execute corretamente as configurações do banco de dados e carregue as informações nas tabelas para o cenário de testes é necessário que o arquivo de configuração do Spring: ``application.properties`` (localizado no caminho ``/src/main/resources``) tenha o seguinte atributo:

Variavel local de configuração:
``spring.profiles.active = dev``


# Execução de funcionalidades da aplicação

Assim que a aplicação do Spring boot estiver inicializada corretamente, os recursos (endpoints) poderão ser acessados através do Postman; localmente (porta 8080).


## Endpoints

Para que seja possível executar os métodos abaixo e realizar as operações da API desenvolvida é necessário utilizar a ferramenta Postman. É possível acompanhar também o comportamento do banco de dados através da ferramenta pgAdmin (já inclusa na instalação do PostgreSQL).

### Clientes

http://localhost:8080/desafio_nexti/clientes

Métodos:
GET: Listar clientes

POST: Inserir clientes

PUT: Alterar clientes

DELETE: Excluir clientes



### Produtos
http://localhost:8080/desafio_nexti/produtos

Métodos:
GET: Listar produtos

POST: Inserir produtos

PUT: Alterar produtos

DELETE: Excluir produtos


### Pedidos
http://localhost:8080/desafio_nexti/pedidos

Métodos:

GET: Listar pedidos

POST: Inserir pedidos

PUT: Alterar pedidos

DELETE: Excluir pedidos


### Pedidos - vincular Clientes
Método: PUT

Vincular clientes
http://localhost:8080/pedidos/desafio_nexti/clienteI/{id}

Desvincular clientes
http://localhost:8080/pedidos/desafio_nexti/clienteR/{id}

### Pedidos - vincular produtos
Método: PUT

Vincular produtos
http://localhost:8080/pedidos/desafio_nexti/produtoI/{id}

Desvincular produtos
http://localhost:8080/pedidos/desafio_nexti/produtoR/{id}

# Conclusão

O projeto foi concluído nesta primeira versão e a API entrega todas as funcionalidades definidas nos requisitos funcionais propostos pela Nexti.


# Autor
Felipe Roberto Sumiya

https://www.linkedin.com/in/felipe-roberto-sumiya-574b76138/
