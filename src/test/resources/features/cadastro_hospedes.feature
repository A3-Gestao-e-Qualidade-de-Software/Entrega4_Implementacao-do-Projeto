Feature: Cadastro de hospede

  Scenario: Cadastrar um novo hospede
    Given Dado que estou na tela de cadastro de hospedes
    When Quando preencho os campos obrigatorios com os seguinte dados:
    | Nome    | sobrenome | dtNascimento | cpf         | genero | endereco | telefone     | email       |
    | Fulano  | Silva     | 01/01/2000   | 48600790000 | m      | MG, BH   | 31999999999  | teste@email |
    And E clico em salvar
    Then o hospede é cadastrado com sucesso

