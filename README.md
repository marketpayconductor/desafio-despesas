# desafio-despesas [![Build Status](https://travis-ci.org/jarddel/desafio-despesas.svg?branch=master)](https://travis-ci.org/jarddel/desafio-despesas)
### Utilização

```
$ {mysql_path}\mysql start
```

```
$ mvn spring-boot:run
```

### Features adicionadas
- OAuth2 com JWT (via profile)
- BasicAuth (via profile)
- ApiDoc (swagger2)
- Migrations (FlyWay)
- Mecanismo de cache (EhCache)
- Pool de conexões (Hikari)

### Dependências
- JDK 1.8+
- Maven 3+
- Spring 4+
- MySQL 5.7+

### Escopo

#### Modelo
![modelo](https://i.imgur.com/JoDnCUo.jpg)

#### Modelo Entidade Relacionamento (MER)
![MER](https://i.imgur.com/rK6wKcK.jpg)

#### Recursos
![Recursos](https://i.imgur.com/OYYy7QP.jpg)


### Possíveis Melhorias
- [ ] ModelMapper para evitar exposição das entidades
