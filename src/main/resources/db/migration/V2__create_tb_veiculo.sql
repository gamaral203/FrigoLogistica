CREATE TABLE tb_veiculo
(
    id           BIGSERIAL PRIMARY KEY,
    tipo_veiculo VARCHAR(255) NOT NULL,
    placa        VARCHAR(6)   NOT NULL UNIQUE,
    marca        VARCHAR(255) NOT NULL,
    modelo       VARCHAR(255) NOT NULL,
    renavam      VARCHAR(11)  NOT NULL UNIQUE,
    cor          VARCHAR(255)
);