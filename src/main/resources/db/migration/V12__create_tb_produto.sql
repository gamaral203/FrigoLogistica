CREATE TABLE tb_produto
(
    id                 BIGSERIAL PRIMARY KEY,
    nome               VARCHAR(255)   NOT NULL,
    descricao          VARCHAR(255),
    preco              NUMERIC(10, 2) NOT NULL,
    quantidade_estoque INTEGER        NOT NULL,
    tipo_produto       VARCHAR(50)    NOT NULL,
    data_cadastro      DATE
);