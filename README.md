# Desafio Conductor de Seleção

[![Build Status](https://travis-ci.org/fagnerlima/desafio-despesas.svg?branch=master)](https://travis-ci.org/fagnerlima/desafio-despesas)

## Dependências

- JDK 1.8+;
- Maven 3+;
- Spring 4+;
- MySQL 5.7+ (em modo de desenvolvimento).

## Executando a aplicação

### Rodando um jar executável

```
java -Dserver.port=$PORT -Dspring.profiles.active=oauth,prod $JAVA_OPTS -jar target/desafio-despesas*.jar
```

### Usando o Maven

```
$ mvn spring-boot:run
```

#### Especificando os profiles

```
mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=basic-auth,test"
```

Até o momento apenas os profiles **basic-auth** e **test** estão disponíveis. Se você não especificar os profiles, ambos serão selecionados automaticamente.

Para executar a aplicação em modo de desenvolvimento, ative apenas o profile **basic-auth**.
