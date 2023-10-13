CREATE TABLE patrimonio (
    id UUID NOT NULL
        CONSTRAINT patrimonio_pk
            PRIMARY KEY,
    valorTotal DOUBLE PRECISION,
    quantidadeTotal INT
);
CREATE TABLE usuario (
    id UUID NOT NULL
        CONSTRAINT usuario_pk
            PRIMARY KEY,
    nome VARCHAR(45),
    email VARCHAR(45),
    senha VARCHAR(45),
    patrimonio_id UUID
        CONSTRAINT usuario_fk_patrimonio_id
            REFERENCES patrimonio
);
CREATE TABLE ativo (
    id UUID NOT NULL
        CONSTRAINT ativo_pk
            PRIMARY KEY,
    nome VARCHAR(45),
    descricao VARCHAR(45),
    categoria VARCHAR(45),
    valor DOUBLE PRECISION,
    patrimonio_id UUID
        CONSTRAINT ativo_fk_patrimonio_id
            REFERENCES patrimonio
);

CREATE TABLE transacao (
    id UUID CONSTRAINT transacao_pk PRIMARY KEY,
    dataDaTransacao DATE,
    valorTotal DOUBLE PRECISION,
    quantidade INT,
    Usuario_id UUID
        CONSTRAINT transacao_fk_usuario_id
            REFERENCES usuario,
    Ativo_id UUID
        CONSTRAINT transacao_fk_ativo_id
            REFERENCES ativo
);
