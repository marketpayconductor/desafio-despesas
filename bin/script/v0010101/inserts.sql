insert into t_pessoa(id_pessoa, cpf, nome) values (-1, "01651247412", "Alexsandro Guedes");
insert into t_conta(id_conta, id_pessoa, saldo_atual, saldo_anterior, numero) values (-1, -1, 100.00, 0, "12345");
insert into t_tipomovimentacao(id_tipo_movimento, codigo_identificador, credito_debito, descricao) values (-3, -40, 'D', 'TRANSFERENCIA DE SALDO DE OUTRA CONTA');
insert into t_tipomovimentacao(id_tipo_movimento, codigo_identificador, credito_debito, descricao) values (-2, -30, 'D', 'TRANSFERENCIA DE SALDO PARA OUTRA CONTA');
insert into t_tipomovimentacao(id_tipo_movimento, codigo_identificador, credito_debito, descricao) values (-1, -20, 'C', 'Receita');