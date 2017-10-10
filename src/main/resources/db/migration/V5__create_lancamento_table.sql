CREATE TABLE lancamento (
    id INTEGER AUTO_INCREMENT NOT NULL,
    id_conta BIGINT NOT NULL,
    tipo ENUM('Receita', 'Despesa') NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    descricao VARCHAR(50) NULL,
    data_atualizacao DATETIME NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_lancamento PRIMARY KEY(id),
    CONSTRAINT fk_lancamento_conta FOREIGN KEY(id_conta) REFERENCES conta(id_usuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
