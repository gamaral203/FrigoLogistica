CREATE TABLE tb_despesas
(

    id              BIGSERIAL PRIMARY KEY,

    descricao       VARCHAR(255)   NOT NULL,

    tipo_despesa    VARCHAR(50)    NOT NULL,

    preco           NUMERIC(10, 2) NOT NULL,

    forma_pagamento VARCHAR(50)    NOT NULL,

    status          VARCHAR(50)    NOT NULL,

    data_despesa    DATE           NOT NULL,

    motorista_id    BIGINT,

    veiculo_id      BIGINT,

    CONSTRAINT fk_despesa_motorista
        FOREIGN KEY (motorista_id)
            REFERENCES tb_motorista (id),

    CONSTRAINT fk_despesa_veiculo
        FOREIGN KEY (veiculo_id)
            REFERENCES tb_veiculo (id)
);