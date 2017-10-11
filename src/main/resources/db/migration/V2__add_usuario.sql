INSERT INTO usuario (nome, cpf, data_nascimento, email, senha) VALUES
    ('Administrador', '11122233344', '1991-10-15', 'admin@desafiodespesas.com', '$2a$06$SEfSmmFIDmHhzZIxl0Nas.81xzfDN7Sw4HAF3PYuyyl1YNIQf.TB6'),
    ('José da Silva', '22233344455', '1985-09-03', 'jose@desafiodespesas.com', '$2a$06$BsIAezgG1SGW.cC2tmyNVO3W9xZK0bS8Wv6UpxpXV42bC5zKox7je'),
    ('Maria Albuquerque', '33344455566', '1987-01-09', 'maria@desafiodespesas.com', '$2a$06$oIRsmWS6NF0YYsh4DYgmOuPHacyakuqPUSRLL6lFxOI1oIWSTdHUm');

INSERT INTO permissao (descricao) VALUES ('ROLE_ADMIN'), ('ROLE_COMUM');

INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 1), (2, 2), (3, 2);