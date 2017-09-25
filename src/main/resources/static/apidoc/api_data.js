define({ "api": [
  {
    "type": "post",
    "url": "/api/conta/cadastrarMovimento",
    "title": "Cadastrar movimentações da contas.",
    "name": "Cadastrar_Movimenta__es",
    "group": "Movimentacao",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "valor",
            "description": "<p>Valor da movimentação</p>"
          },
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "numeroConta",
            "description": "<p>Nomero da conta da movimentação</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cpf",
            "description": "<p>CPF pessoa da conta</p>"
          },
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "codigoTipoMovimentacao",
            "description": "<p>Código referente ao tipo da movimentação</p>"
          },
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "observacao",
            "description": "<p>Observação da movimentação</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example: ",
          "content": "{\n\t\"valor\" : 100.0,\n\t\"numeroConta\" : \"12345\",\n\t\"cpf\" : \"83017734803\",\n\t\"codigoTipoMovimentacao\" : -20,\n\t\"observacao\" : \"Teste de cadastro de crédito\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "retornoOperacao",
            "description": "<p>Mensagem de retorno.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 200 OK \n{\n\t\"retornoOperacao\":{\n\t\"codigo\": \"00\",\n\t\"mensagem\": \"Operação realizada com sucesso!\"\n\t}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/br/com/conductor/despesasapp/api/controller/ContaController.java",
    "groupTitle": "Movimentacao"
  },
  {
    "type": "post",
    "url": "/api/conta/historicoMovimentacoes",
    "title": "Histórico das movimentações da conta.",
    "name": "Hist_rico_de_Movimenta__es",
    "group": "Movimentacao",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "numeroConta",
            "description": "<p>Nomero da conta da movimentação</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example: ",
          "content": "{\n\t\"numeroConta\" : \"12345\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "retornoOperacao",
            "description": "<p>Mensagem de retorno</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 200 OK \n{\n\t\"retornoOperacao\":{\n\t\"codigo\": \"00\",\n\t\"mensagem\": \"Operação realizada com sucesso!\"\n\t},\n\t\"listaDeMovimentacoes\":[\n\t\t{\"data\": \"24/09/2017\", \"observacao\": \"Transerência de saldo de outra conta\", \"codigoTipoMovimentacao\": -40, \"valor\": 100…},\n\t\t{\"data\": \"24/09/2017\", \"observacao\": \"Transferência de saldo para outra conta\", \"codigoTipoMovimentacao\": -30,…},\n\t\t{\"data\": \"24/09/2017\", \"observacao\": \"Transerência de saldo de outra conta\", \"codigoTipoMovimentacao\": -40, \"valor\": 100…}\n\t]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/br/com/conductor/despesasapp/api/controller/ContaController.java",
    "groupTitle": "Movimentacao"
  },
  {
    "type": "post",
    "url": "/api/conta/consultarSaldo",
    "title": "Consultar saldo da conta.",
    "name": "Consultar_saldo",
    "group": "Saldo",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "numeroConta",
            "description": "<p>Nomero da conta da movimentação</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example: ",
          "content": "{\n\t\"numeroConta\" : \"12345\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "BigDecimal",
            "optional": false,
            "field": "saldo",
            "description": "<p>Saldo atual da conta</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "retornoOperacao",
            "description": "<p>Mensagem de retorno</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 200 OK \n{\n\t\"retornoOperacao\":{\n\t\"codigo\": \"00\",\n\t\"mensagem\": \"Operação realizada com sucesso!\"\n\t},\n\t\"saldo\": 14.5\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/br/com/conductor/despesasapp/api/controller/ContaController.java",
    "groupTitle": "Saldo"
  },
  {
    "type": "post",
    "url": "/api/conta/transferirSaldo",
    "title": "Tranferência de saldo entre contas.",
    "name": "Transfer_ncia_de_saldo",
    "group": "Saldo",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "valor",
            "description": "<p>Valor da movimentação</p>"
          },
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "numeroContaOrigem",
            "description": "<p>Nomero da conta de origigem da movimentação</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cpfContaOrigem",
            "description": "<p>CPF pessoa da conta de origem</p>"
          },
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "numeroContaDestino",
            "description": "<p>Nomero da conta de destino da movimentação</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cpfContaDestino",
            "description": "<p>CPF pessoa da conta de destino</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example: ",
          "content": "{\n\t\"numeroContaOrigem\" : 12345,\n\t\"cpfContaOrigem\" : \"01651247412\",\n\t\"numeroContaDestino\" : 54321,\n\t\"cpfContaDestino\" : \"23197456700\",\n\t\"valor\" : 10.0\n\t}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "retornoOperacao",
            "description": "<p>Mensagem de retorno</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 200 OK \n{\n\t\"retornoOperacao\":{\n\t\"codigo\": \"00\",\n\t\"mensagem\": \"Operação realizada com sucesso!\"\n\t}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/br/com/conductor/despesasapp/api/controller/ContaController.java",
    "groupTitle": "Saldo"
  },
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "src/main/resources/static/apidoc/main.js",
    "group": "_home_alexsandro_git_desafio_despesas_github_src_main_resources_static_apidoc_main_js",
    "groupTitle": "_home_alexsandro_git_desafio_despesas_github_src_main_resources_static_apidoc_main_js",
    "name": ""
  }
] });
