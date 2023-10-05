-- Criando tabela Quarto
CREATE TABLE Quarto (
	ID_Quarto INT auto_increment PRIMARY KEY,
    Num_Quarto INT NOT NULL UNIQUE,
    Andar_Quarto INT NOT NULL CHECK(Andar_Quarto IN (1, 2, 3)),
    Tipo_Quarto VARCHAR(8) NOT NULL CHECK(Tipo_Quarto IN ('Standard', 'Comfort', 'Deluxe')),
    Preco_Noite DECIMAL(7, 2) NOT NULL,
    Status_Ocupacao VARCHAR(10) NOT NULL CHECK(Status_Ocupacao IN ('Disponível', 'Ocupado')),
    Descricao TEXT
);

--  Inserindo registros fixos: 4 quartos por andar (3 andares), dos tipos Standard(R$300,00), Comfort(R$400,00) e Deluxe(R$500,00).
INSERT INTO
	Quarto 
    (Num_Quarto, Andar_Quarto, Tipo_Quarto, Preco_Noite, Status_Ocupacao, Descricao)
VALUES
	(31, 3, 'Comfort', 400.00, 'Disponível', 'Quarto confortável com ar condicionado, suíte, cama de casal, possúi serviço de limpeza, frigobar.'),
    (32, 3, 'Deluxe', 500.00, 'Disponível', 'Nossa Suíte Deluxe é a escolha definitiva para quem busca luxo e requinte. Essa suíte espaçosa oferece uma área de estar separada e uma decoração elegante. Os hóspedes podem desfrutar de vistas panorâmicas deslumbrantes e relaxar em uma cama luxuosa. As instalações incluem um banheiro privativo, ar condicionado, TV com canais premium e acesso à internet de alta velocidade. Além disso, os serviços de concierge estão disponíveis para atender a todas as necessidades dos hóspedes durante a estadia.'),
    (33, 3, 'Deluxe', 500.00, 'Disponível', 'Nossa Suíte Deluxe é a escolha definitiva para quem busca luxo e requinte. Essa suíte espaçosa oferece uma área de estar separada e uma decoração elegante. Os hóspedes podem desfrutar de vistas panorâmicas deslumbrantes e relaxar em uma cama luxuosa. As instalações incluem um banheiro privativo, ar condicionado, TV com canais premium e acesso à internet de alta velocidade. Além disso, os serviços de concierge estão disponíveis para atender a todas as necessidades dos hóspedes durante a estadia.'),
    (34, 3, 'Standard', 300.00, 'Disponível', 'Quarto confortável com ar condicionado, suíte, cama de casal, possúi serviço de limpeza, frigobar.'),
    (21, 2, 'Comfort', 400.00, 'Disponível', 'Quarto confortável com ar condicionado, suíte, cama de casal, possúi serviço de limpeza, frigobar.'),
	(22, 2, 'Comfort', 400.00, 'Disponível', 'Quarto confortável com ar condicionado, suíte, cama de casal, possúi serviço de limpeza, frigobar.'),
    (23, 2, 'Standard', 300.00, 'Disponível', 'Quarto confortável com ar condicionado, suíte, cama de casal, possúi serviço de limpeza, frigobar.'),
    (24, 2, 'Deluxe', 500.00, 'Disponível', 'Nossa Suíte Deluxe é a escolha definitiva para quem busca luxo e requinte. Essa suíte espaçosa oferece uma área de estar separada e uma decoração elegante. Os hóspedes podem desfrutar de vistas panorâmicas deslumbrantes e relaxar em uma cama luxuosa. As instalações incluem um banheiro privativo, ar condicionado, TV com canais premium e acesso à internet de alta velocidade. Além disso, os serviços de concierge estão disponíveis para atender a todas as necessidades dos hóspedes durante a estadia.'),
    (11, 1, 'Standard', 300.00, 'Disponível', 'Quarto confortável com ar condicionado, suíte, cama de casal, possúi serviço de limpeza, frigobar.'),
    (12, 1, 'Standard', 300.00, 'Disponível', 'Quarto confortável com ar condicionado, suíte, cama de casal, possúi serviço de limpeza, frigobar.'),
    (13, 1, 'Comfort', 400.00, 'Disponível', 'Quarto confortável com ar condicionado, suíte, cama de casal, possúi serviço de limpeza, frigobar.'),
    (14, 1, 'Deluxe', 500.00, 'Disponível', 'Nossa Suíte Deluxe é a escolha definitiva para quem busca luxo e requinte. Essa suíte espaçosa oferece uma área de estar separada e uma decoração elegante. Os hóspedes podem desfrutar de vistas panorâmicas deslumbrantes e relaxar em uma cama luxuosa. As instalações incluem um banheiro privativo, ar condicionado, TV com canais premium e acesso à internet de alta velocidade. Além disso, os serviços de concierge estão disponíveis para atender a todas as necessidades dos hóspedes durante a estadia.');


--  Criando tabela de Hospedes
CREATE TABLE Hospedes (
	ID_Hospede INT auto_increment PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Sobrenome Varchar(100) NOT NULL,
    DT_Nascimento DATE NOT NULL,
    CPF VARCHAR(11) NOT NULL UNIQUE CHECK (length(CPF) = 11),
    Genero CHAR(1) NOT NULL CHECK (Genero IN('M', 'F')),
    Endereco VARCHAR(255) NOT NULL,
    Telefone VARCHAR(15) NOT NULL,
    Email VARCHAR(255) UNIQUE
);


--  Criando Tabela Status_Reservas
CREATE TABLE
	Status_Reservas(
    ID_Status_Reserva INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Status_Reserva VARCHAR(20) NOT NULL UNIQUE CHECK(
		Status_Reserva IN ('Confirmada', 'Pendente', 'Cancelada', 'Check-in', 'Check-out', 'No-show', 'Concluída')
        )
	);

--  Inserindo valores fixos no campo Status_Reserva
INSERT INTO
	Status_Reservas
    (Status_Reserva)
VALUES
	('Confirmada'),
    ('Pendente'),
    ('Cancelada'),
    ('Check-in'),
    ('Check-out'),
    ('No-show'),
    ('Concluída');


--  Criando tabela de Reservas
CREATE TABLE
	Reservas(
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_Hospede INT NOT NULL,
    ID_Quarto INT NOT NULL,
    DT_Check_IN DATE NOT NULL,
    DT_Check_OUT DATE NOT NULL,
    DT_Reserva DATE NOT NULL,
    ID_Status_Reserva INT NOT NULL,
    Status_Ativa BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ID_Hospede) REFERENCES Hospedes(ID_Hospede),
    FOREIGN KEY (ID_Quarto) REFERENCES Quarto(ID_Quarto),
    FOREIGN KEY (ID_Status_Reserva) REFERENCES Status_Reservas(ID_Status_Reserva)
    );
    

--  Criando tabela Usuario
CREATE TABLE
	Usuario(
    ID_Usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NomeUsuario VARCHAR(100) NOT NULL,
    Funcional VARCHAR(9) NOT NULL UNIQUE CHECK(LENGTH(Funcional) = 9),
    Senha VARCHAR(255) NOT NULL,
    CONSTRAINT chk_Senha_SemEspaco CHECK (Senha NOT LIKE '% %')
    );


USE hotel;
SHOW TABLES;
SELECT * FROM quarto;
SELECT * FROM hospedes;
SELECT * FROM reservas;
SELECT * FROM Status_Reservas ORDER BY ID_Status_Reserva;
SELECT * FROM usuario;