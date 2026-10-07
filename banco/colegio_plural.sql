DROP TABLE IF EXISTS atendimento;
DROP TABLE IF EXISTS crianca;
DROP TABLE IF EXISTS responsavel;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_expiracao TIMESTAMP
);

CREATE TABLE responsavel (
    id_responsavel SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(200)
);

CREATE TABLE crianca (
    id_crianca SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    cpf VARCHAR(255),
    id_responsavel INTEGER NOT NULL,

    FOREIGN KEY (id_responsavel)
        REFERENCES responsavel(id_responsavel)
);

CREATE TABLE atendimento (
    id_atendimento SERIAL PRIMARY KEY,
    data_atendimento DATE NOT NULL,
    descricao VARCHAR(500),
    id_responsavel INTEGER NOT NULL,
    id_crianca INTEGER NOT NULL,

    FOREIGN KEY (id_responsavel)
        REFERENCES responsavel(id_responsavel),

    FOREIGN KEY (id_crianca)
        REFERENCES crianca(id_crianca)
);

INSERT INTO usuario
(nome, email, senha, data_expiracao)
VALUES
('João Silva', 'joao@plural.com', '123456', '2026-12-31 23:59:59'),
('Maria Souza', 'maria@plural.com', '123456', '2026-12-31 23:59:59'),
('Carlos Oliveira', 'carlos@plural.com', '123456', '2026-12-31 23:59:59');

INSERT INTO responsavel
(nome, cpf, telefone, email, endereco)
VALUES
('Ana Silva', '11111111111', '21999990001', 'ana@email.com', 'Rua A, 100'),
('Pedro Souza', '22222222222', '21999990002', 'pedro@email.com', 'Rua B, 200'),
('Mariana Oliveira', '33333333333', '21999990003', 'mariana@email.com', 'Rua C, 300');

INSERT INTO crianca
(nome, data_nascimento, cpf, id_responsavel)
VALUES
('Lucas Silva', '2012-05-10', '44444444444', 1),
('Gabriel Souza', '2013-08-20', '55555555555', 2),
('Julia Oliveira', '2011-03-15', '66666666666', 3);

INSERT INTO atendimento
(data_atendimento, descricao, id_responsavel, id_crianca)
VALUES
('2026-09-10', 'Atendimento inicial do aluno.', 1, 1),
('2026-09-20', 'Acompanhamento escolar.', 2, 2),
('2026-10-01', 'Avaliacao e acompanhamento do aluno.', 3, 3);