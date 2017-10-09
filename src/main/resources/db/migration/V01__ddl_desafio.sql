CREATE TABLE usuario (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  ativo bit(1) NOT NULL,
  email varchar(32) NOT NULL,
  login varchar(32) NOT NULL,
  senha varchar(64) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_usuario_email (email),
  UNIQUE KEY uk_usuario_login (login)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  papel varchar(32) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_permissao_papel (papel)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
  usuario bigint(20) NOT NULL,
  permissao bigint(20) NOT NULL,
  FOREIGN KEY (usuario) REFERENCES usuario(id),
  FOREIGN KEY (permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE conta (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  ativo bit(1) NOT NULL,
  data_criacao date DEFAULT NULL,
  cpf varchar(255) NOT NULL,
  data_nascimento date NOT NULL,
  nome varchar(255) NOT NULL,
  saldo decimal(19,2) DEFAULT NULL,
  tipo varchar(255) NOT NULL,
  version int(11) DEFAULT NULL,
  usuario bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_conta_cpf (cpf),
  FOREIGN KEY (usuario) REFERENCES usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lancamento (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  tipo varchar(31) NOT NULL,
  data datetime DEFAULT NOW(),
  valor decimal(19,2) NOT NULL,
  conta_destino bigint(20) DEFAULT NULL,
  conta_origem bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (conta_destino) REFERENCES conta(id),
  FOREIGN KEY (conta_origem) REFERENCES conta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;