CREATE TABLE tb_funcionario (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(11) UNIQUE,
    email VARCHAR(150) UNIQUE,
    rg VARCHAR(14) UNIQUE,
    telefone VARCHAR(20)
);

CREATE TABLE tb_chaveiro (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    dispositivo VARCHAR(100)
);
CREATE TABLE tb_patio (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY
);

CREATE TABLE tb_localizacao (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    rua VARCHAR(255),
    numero INT,
    cidade VARCHAR(100),
    estado VARCHAR(100),
    patio_id BIGINT UNIQUE,
    CONSTRAINT fk_localizacao_patio FOREIGN KEY (patio_id) REFERENCES tb_patio(id)
);

CREATE TABLE tb_moto (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    modelo VARCHAR(100),
    cor VARCHAR(50),
    placa VARCHAR(20) UNIQUE,
    dataFabricacao DATE,
    status VARCHAR(20),
    patio_id BIGINT,
    chaveiro_id BIGINT UNIQUE,
    CONSTRAINT fk_moto_patio FOREIGN KEY (patio_id) REFERENCES tb_patio(id),
    CONSTRAINT fk_moto_chaveiro FOREIGN KEY (chaveiro_id) REFERENCES tb_chaveiro(id)
);

CREATE TABLE funcionario_patio (
    funcionario_id BIGINT,
    patio_id BIGINT,
    PRIMARY KEY (funcionario_id, patio_id),
    CONSTRAINT fk_funcionario_patio_func FOREIGN KEY (funcionario_id) REFERENCES tb_funcionario(id),
    CONSTRAINT fk_funcionario_patio_patio FOREIGN KEY (patio_id) REFERENCES tb_patio(id)
);
