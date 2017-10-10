INSERT INTO usuario (id, ativo, email, login, senha) VALUES (DEFAULT, TRUE, 'admin@email.com', 'admin', '$2a$10$4tdOm.AIWfhyg3aiYNEBwu7GRhdZOe0Ptjrp5p16YrYhROmUw1A9a');
INSERT INTO usuario (id, ativo, email, login, senha) VALUES (DEFAULT, TRUE, 'fulano@email.com', 'fulano', '$2a$10$4tdOm.AIWfhyg3aiYNEBwu7GRhdZOe0Ptjrp5p16YrYhROmUw1A9a');
INSERT INTO usuario (id, ativo, email, login, senha) VALUES (DEFAULT, TRUE, 'cicrano@email.com', 'cicrano', '$2a$10$4tdOm.AIWfhyg3aiYNEBwu7GRhdZOe0Ptjrp5p16YrYhROmUw1A9a');
INSERT INTO usuario (id, ativo, email, login, senha) VALUES (DEFAULT, FALSE, 'beltrano@email.com', 'beltrano', '$2a$10$4tdOm.AIWfhyg3aiYNEBwu7GRhdZOe0Ptjrp5p16YrYhROmUw1A9a');

INSERT INTO usuario_permissao (usuario, permissao) VALUES ((SELECT id FROM usuario WHERE login = 'admin'), (SELECT id FROM permissao WHERE papel = 'ROLE_ADMIN'));
INSERT INTO usuario_permissao (usuario, permissao) VALUES ((SELECT id FROM usuario WHERE login = 'fulano'), (SELECT id FROM permissao WHERE papel = 'ROLE_COMUM'));
INSERT INTO usuario_permissao (usuario, permissao) VALUES ((SELECT id FROM usuario WHERE login = 'cicrano'), (SELECT id FROM permissao WHERE papel = 'ROLE_COMUM'));
INSERT INTO usuario_permissao (usuario, permissao) VALUES ((SELECT id FROM usuario WHERE login = 'beltrano'), (SELECT id FROM permissao WHERE papel = 'ROLE_COMUM'));

INSERT INTO conta (id, ativo, data_criacao, cpf, data_nascimento, nome, saldo, tipo, version, usuario) 
VALUES (DEFAULT, TRUE, NOW(), '11111111111', '1990-01-01', 'Administrador', 0, 'PREMIUM', 0, (SELECT id FROM usuario WHERE login = 'admin'));

INSERT INTO conta (id, ativo, data_criacao, cpf, data_nascimento, nome, saldo, tipo, version, usuario) 
VALUES (DEFAULT, TRUE, NOW(), '22222222222', '1990-02-02', 'Fulano', 0, 'COMUM', 0, (SELECT id FROM usuario WHERE login = 'fulano'));

INSERT INTO conta (id, ativo, data_criacao, cpf, data_nascimento, nome, saldo, tipo, version, usuario) 
VALUES (DEFAULT, TRUE, NOW(), '33333333333', '1990-03-03', 'Cicrano', 0, 'COMUM', 0, (SELECT id FROM usuario WHERE login = 'cicrano'));
