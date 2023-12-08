# language:pt

  Funcionalidade: Cadastro de Hospede

    Cenario: Cadastrar um novo hospede
      Dado que o usuario está na tela de cadastro de hospede
      Quando o usuario insere os seguintes dados:
        | nome    | sobrenome | dtNascimento | cpf         | genero | endereco | telefone     | email        |
        | Fulano  | Silva     | 01/01/2000   | 19859145067 | m      | MG, BH   | 31999999999  | fulano@email |
      Entao o sistema deve cadastrar o hospede no banco de dados

    Cenario: Cadastrar um novo hospede com dados invalidos
      Dado que o usuario está na tela de cadastro de hospede
      Quando o usuario insere os seguintes dados:
        | nome    | sobrenome | dtNascimento | cpf         | genero | endereco | telefone     | email       |
        |         |           |              |             |        |          |              |             |
      Entao o sistema não deve cadastrar o hospede no banco de dados

    Cenario: Cadastrar um novo hospede com CPF ja existente
      Dado que o usuario está na tela de cadastro de hospede
      Quando o usuario insere os seguintes dados:
        | nome    | sobrenome | dtNascimento | cpf         | genero | endereco | telefone     | email         |
        | Ciclano | Oliveira  | 01/01/1980   | 19859145067 | m      | Rua Teste| 1199999999   | ciclano@email |
      Entao o sistema não deve cadastrar o hospede no banco de dados