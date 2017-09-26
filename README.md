# desafio-despesas - Caio César Falconi

### Sobre o Projeto 
	A API contendo os serviços deste projeto foi desenvolvida utilizando a arquitetura REST. Foram utilizados também o framework Spring boot, JUnit e para o gerenciamento de dependências o Maven.

### Execução do Projeto 
	- Banco de Dados:
    ```
	É necessário criar o banco de dados "desafio_despesas";
    As configurações do banco de dados (tais como url, username e password)  estão no arquivo "application.properties";
    ```
	
	- Maven:
	```
	* Via IDE: É necessário executar o build com o parâmetro "clean install";
    ```
	
	- Disponibilizar API:
	```
	* Via IDE: É necessário executar como "Java Application" a classe DesafioApplication.java;
    ```
	
	- Testar Recursos API:
	```
	Para testar os serviços da API sugiro o Postman (plugin do Google Chrome), o SoapUI ou alguma outra ferramenta para consumo e testes de serviços REST;
    ```

### API
	- Conta
	* Criar Conta:
		Método: 		POST 
		URL:			http://localhost:8080/conta/
		Exemplo JSon:	{"numeroConta":"1234567", "descricao": "Conta Teste", "saldo":1000}
		
    * Listar Contas:	
		Método: GET 
		URL: 	http://localhost:8080/conta/
		
	* Consultar Conta:
		Método: GET 
		URL: 	http://localhost:8080/conta/{idConta}
	
	* Apagar Conta:
		Método: DELETE
		URL: 	http://localhost:8080/conta/
		
	- Lançamento (0-Receita / 1-Despesa)
	* Adicionar Lançamento:
		Método: 		POST 
		URL:			http://localhost:8080/conta/{idConta}/lancamento/
		Exemplo JSon:	{"descricao":"Salario", "valor": 3000, "data":"2017-05-05", "tipoLancamento": 0 }
		
	* Trasferir:
	Método: 		POST 
	URL:			http://localhost:8080/conta/{idContaOrigem}/transferir/{idContaDestino}/
	Exemplo JSon:	{"descricao":"Pagamento Dívida", "valor": 200}
	
	* Histórico Movimentações:
		Método: GET 
		URL: 	http://localhost:8080/conta/{idConta}/lancamento/	
		
		
		