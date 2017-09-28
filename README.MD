
##Desafio despesas: Glaube Oliveira Nóbrega

###Para executar o projeto

1. Criação do banco de dados "desafio_glaube"
2. As configurações do banco de dados (url, username e password) estão no arquivo "application.properties"
3. Será necessário executar como "Java Application" a classe DesafioApplication.java;

##O QUE FOI FEITO
Aplicação rest, usando Maven, JDK 1.8, JPA e Spring;

##API USUARIO

##ADICIONAR USUARIO
	
	Método: POST
	URL: localhost:8080/api/usuario
	Request: {"cpf" : "96734291085", "nome": "João Maria"}
	
##DESATIVAR USUARIO

	Método: DELTE
	URL: localhost:8080/api/usuario/{cpf}

##REMOVER LISTAR USUARIOS

	Método: GET
	URL: localhost:8080/api/usuario


##API CONTA

##ADICIONAR CONTAR
	
	Método: POST
	URL: localhost:8080/api/conta
	Request: {"cpf" : "03969496497","numeroConta" : "12345", "valor" : 100}

##CONSULTAR SALDO

	Método: GET
	URL: localhost:8080/api/conta/consultarSaldo/{numeroConta}
	

##DESATIVAR CONTA

	Método: DELETE
	URL: localhost:8080/api/conta/{numeroConta}

##TRANSFERÊNCIA 

	Método: POST
	URL: localhost:8080/api/conta/transferir
	Request: { "cpfOrigem" : "12345678901","cpfDestino" : "23456789012","contaOrigem" : "12345", "contaDestino" : "54321", "valor" : 100}

##TRANSAÇÃO 

	TIPO DE OPERAÇÃO: 0-Receita / 1-Despesa
	Método: POST
	URL: localhost:8080/api/conta/transacao
	Request: {"cpf" : "03969496497","numeroConta" : "12345", "tipoOperacao" : 1, "valor" : 100, "observacao" : "Carro"}
	
##EXTRATO OPERAÇÃO

	Método: POST
	URL: localhost:8080/api/conta/extrato
	Request: {"cpf" : "03969496497","numeroConta" : "12345"}


