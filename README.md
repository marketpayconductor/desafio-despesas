# desafio-despesas

### Configuração e execução da aplicação 
Para executar o projeto é necessário apenas executar o arquivo SpringBootRunApp.java, passando a argumento -Dspring.profiles.active=local.

No caso da configuração do banco de dados e do servidor, isso pode ser realizado no arquivo application.yml.  


### Especificações
  Este projeto foi feito usando as seguintes tecnologias:
  - Java 8;
  - Maven 3;
  - Spring Framework 4;
  - Spring Boot 1.5;
  - HikariCP;
  - Apache Commons;

### URLs Rest
  Este projeto possui as seguintes urls:
  
  - Conta:  
    ```
    * **POST:** http://localhost:8080/app/api/conta/    
    * **GET:** http://localhost:8080/app/api/conta/{id}    
    * **GET:** http://localhost:8080/app/api/conta/    
    * **PUT:** http://localhost:8080/app/api/conta/{id}     
    ```

  - Despesa:  
    ```
    * **GET:** http://localhost:8080/app/api/despesa/   
    * **POST:** http://localhost:8080/app/api/despesa/   
    * **PUT:** http://localhost:8080/app/api/despesa/{id}       
    * **PUT: ** http://localhost:8080/app/api/despesa/pagar/{id}   
    * **PUT:** http://localhost:8080/app/api/despesa/cancelar/{id}   
    * **GET:** http://localhost:8080/app/api/despesa/{id}   
    ```
    
  - Lançamento de Conta:  
    ```
    * **GET:** http://localhost:8080/app/api/lancamentoconta/       
    * **GET:** http://localhost:8080/app/api/lancamentoconta/{id}       
    ```

  - Movimentação entre Contas:  
    ```
    * **GET:** http://localhost:8080/app/api/movimentacaoconta/    
    * **GET:** http://localhost:8080/app/api/movimentacaoconta/{id}    
    * **POST:** http://localhost:8080/app/api/movimentacaoconta/    
    ```
  - Receita:  
    ```
    * **GET:** http://localhost:8080/app/api/receita/{id}    
    * **POST:** http://localhost:8080/app/api/receita/    
    * **PUT:** http://localhost:8080/app/api/receita/{id}    
    * **PUT:** http://localhost:8080/app/api/receita/receber/{id}    
    * **PUT:** http://localhost:8080/app/api/receita/cancelar/{id}    
    * **GET:** http://localhost:8080/app/api/receita/    
    ```

  - Tipo de Despesa:  
    ```
    * **GET:** http://localhost:8080/app/api/tipodespesa/{id}    
    * **POST:** http://localhost:8080/app/api/tipodespesa/    
    * **PUT:** http://localhost:8080/app/api/tipodespesa/{id}    
    * **GET:** http://localhost:8080/app/api/tipodespesa/    
    ```

  - Tipo de Receita:  
    ```
    * **GET:** http://localhost:8080/app/api/tiporeceita/{id}    
    * **POST:** http://localhost:8080/app/api/tiporeceita/    
    * **GET:** http://localhost:8080/app/api/tiporeceita/    
    * **PUT:** http://localhost:8080/app/api/tiporeceita/{id}    
    ```

  - Usuário:  
    ```
    * **GET:** http://localhost:8080/app/api/usuario/    
    * **GET:** http://localhost:8080/app/api/usuario/{id}    
    * **POST:** http://localhost:8080/app/api/usuario/    
    * **PUT:** http://localhost:8080/app/api/usuario/{id}    
    ```


