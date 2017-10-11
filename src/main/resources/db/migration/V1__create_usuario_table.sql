CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(150) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao DATETIME NOT NULL DEFAULT NOW(),
    data_atualizacao DATETIME NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_usuario PRIMARY KEY(id),
    CONSTRAINT uk_usuario_email UNIQUE KEY(email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
    id BIGINT AUTO_INCREMENT NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    CONSTRAINT pk_permissao PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
    id_usuario BIGINT NOT NULL,
    id_permissao BIGINT NOT NULL,
    CONSTRAINT pk_usuario_permissao PRIMARY KEY(id_usuario, id_permissao),
    CONSTRAINT fk_usu_perm_usuario FOREIGN KEY(id_usuario) REFERENCES usuario(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_usu_perm_permissao FOREIGN KEY(id_permissao) REFERENCES permissao(id)
        ON UPDATE CASCADE ON DELETE CASCADE
);