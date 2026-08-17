CREATE TABLE tbl_agendamento (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    visitante_id BIGINT NOT NULL,

    data DATE NOT NULL,

    horario TIME NOT NULL,

    quantidade_pessoas INTEGER NOT NULL,

    status VARCHAR(30) NOT NULL,

    data_criacao TIMESTAMP NOT NULL,

    CONSTRAINT fk_agendamento_visitante
        FOREIGN KEY (visitante_id)
        REFERENCES tbl_visitante(id)
        ON DELETE RESTRICT
);