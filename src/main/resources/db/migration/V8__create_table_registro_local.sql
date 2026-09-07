CREATE TABLE registro_local (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome_responsavel VARCHAR(255) NOT NULL,
    codigo_registro VARCHAR(255) NOT NULL UNIQUE,
    quantidade_pessoas INTEGER NOT NULL,
    data DATE NOT NULL,
    status VARCHAR(20) NOT NULL
);