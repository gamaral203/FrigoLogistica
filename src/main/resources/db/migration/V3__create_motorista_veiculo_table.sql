CREATE TABLE motorista_veiculo
(
    motorista_id BIGINT NOT NULL,
    veiculo_id   BIGINT NOT NULL,

    PRIMARY KEY (motorista_id, veiculo_id),

    CONSTRAINT fk_motorista_veiculo_motorista
        FOREIGN KEY (motorista_id)
            REFERENCES tb_motorista (id),

    CONSTRAINT fk_motorista_veiculo_veiculo
        FOREIGN KEY (veiculo_id)
            REFERENCES tb_veiculo (id)
);