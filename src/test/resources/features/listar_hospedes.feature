# language:pt

Funcionalidade: Listar Hospedes

  Cenario: Listar hospedes quando há hospedes cadastrados
    Dado que existem hospedes cadastrados no sistema
    Quando o usuario acessa a opção de listar hospedes
    Entao uma lista de hospedes deve ser retornada

    Cenario: Listar hospedes quando nao ha dados cadastrados
      Dado que nao existem hospedes cadastrados no sistema
      Quando o usuario acessa a opcao de listar hospedes
      Entao uma lista vazia deve ser retornada