CREATE TABLE conta (
    id_usuario BIGINT NOT NULL,
    saldo DECIMAL(10,2) NOT NULL DEFAULT 0,
    data_atualizacao DATETIME DEFAULT NOW(),
    CONSTRAINT pk_conta PRIMARY KEY(id_usuario),
    CONSTRAINT fk_conta_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
