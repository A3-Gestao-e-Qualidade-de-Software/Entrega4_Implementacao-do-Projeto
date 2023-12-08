# language:pt

  Funcionalidade: Cadastro de Usuario

    Cenario: Cadastro de um novo usuario com sucesso
      Dado que foram preenchidos os seguintes dados:
      | NomeUsuario   | Funcional | Senha |
      | Joao da Silva | JoaoSilva | 12345 |
      Quando o usuario acessa a opcao de cadastrar usuario
      Entao o usuario deve ser criado com sucesso

    Cenario: Cadastro de um usuario com dados invalidos
      Dado que foram preenchidos os seguintes dados:
        | NomeUsuario   | Funcional | Senha |
        |               |           |       |
      Quando o usuario acessa a opcao de cadastrar usuario
      Entao o usuario nao deve ser criado