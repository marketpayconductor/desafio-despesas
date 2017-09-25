# PROJETO LUIZ CARLOS FERREIRA

### Um pouco sobre o que foi feito
O projeto foi criado utilizando da Tecnologia Rest, juntamenta com Spring-boot e maven, este projeto encontra-se organizado por pacotes de acordo com suas reponsabilidades, para que assim possa atender as exigências minimas do projeto.

### Como executar o projeto

  Primeiramento o projeto encontra-se dentro da pasta desafiodespesas, é um projeto utilizando maven, caso seja executado por linha de comando deve-se primeiro executar o seguinte comando:
         mvn clean install
  Após a execução, já que é um projeto em estrutura maven, dentro da pasta resources, encontra-se um arquivo chamado application.properties, nele é que se encontra as configurações de Database nescessárias para a execução do projeto. Após o passo anterior, é nescessário executar o projeto com o seguinte comando:
         mvn clean spring-boot:run
Dentro da classe DesafioController encontra-se exemplo das requisições que podem ser utilizadas para testes.
Caso a execução seja via IDE, importar o projeto como maven project, e dentro da Classe DesafiodespesasApplication clicar no botão direito e Run As





