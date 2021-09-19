CREATE SEQUENCE hibernate_sequence START 200 INCREMENT 1;

CREATE TABLE avaliacao (
    id bigint NOT NULL,
    comentario character varying(500),
    nome_avaliadora character varying(255),
    nota smallint NOT NULL,
    data TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_empresa bigint
);

CREATE TABLE endereco (
    id bigint NOT NULL,
    bairro character varying(50),
    complemento character varying(50),
    logradouro character varying(255) NOT NULL,
    municipio_ibge integer NOT NULL,
    numero character varying(10) NOT NULL
);

CREATE TABLE feedback (
    id bigint NOT NULL,
    comentario character varying(500) NOT NULL,
    pessoa_fisica_id bigint
);

CREATE TABLE links (
    id bigint NOT NULL,
    facebook character varying(255),
    git_hub character varying(255),
    linked_in character varying(255),
    portfolio character varying(255)
);

CREATE TABLE pessoa (
    id bigint NOT NULL,
    tipo_pessoa character varying(31),
    email character varying(100),
    nome character varying(255)
);

CREATE TABLE pessoa_fisica (
    id bigint NOT NULL,
    feedback_id bigint,
    links_id bigint
);

CREATE TABLE pessoa_juridica (
    id bigint NOT NULL,
    area_atuacao character varying(100),
    cnpj character varying(14),
    descricao character varying(255),
    porte_empresa character varying(255),
    site character varying(255),
    id_endereco bigint
);

CREATE TABLE telefone (
    id bigint NOT NULL,
    ddd character varying(2),
    ddi character varying(3),
    numero character varying(10),
    pessoa_id bigint
);

CREATE TABLE vaga (
    id bigint NOT NULL,
    area_atuacao character varying(100) NOT NULL,
    cargo character varying(50) NOT NULL,
    descricao character varying(255) NOT NULL,
    localidade character varying(100),
    valor double precision,
    empresa_id bigint
);

ALTER TABLE avaliacao
    ADD CONSTRAINT avaliacao_pkey PRIMARY KEY (id);

ALTER TABLE endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);

ALTER TABLE feedback
    ADD CONSTRAINT feedback_pkey PRIMARY KEY (id);

ALTER TABLE links
    ADD CONSTRAINT links_pkey PRIMARY KEY (id);

ALTER TABLE pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);

ALTER TABLE pessoa_fisica
    ADD CONSTRAINT pessoa_fisica_pkey PRIMARY KEY (id);

ALTER TABLE pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_cnpj_key UNIQUE (cnpj);

ALTER TABLE pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_pkey PRIMARY KEY (id);

ALTER TABLE telefone
    ADD CONSTRAINT telefone_pkey PRIMARY KEY (id);

ALTER TABLE vaga
    ADD CONSTRAINT vaga_pkey PRIMARY KEY (id);

ALTER TABLE avaliacao
    ADD CONSTRAINT fk_avaliacao_pessoa_juridica_id FOREIGN KEY (id_empresa) REFERENCES pessoa_juridica(id);

ALTER TABLE feedback
    ADD CONSTRAINT fk_feedback_pf FOREIGN KEY (pessoa_fisica_id) REFERENCES pessoa_fisica(id);

ALTER TABLE pessoa_fisica
    ADD CONSTRAINT fk_pessoa_fisica_pessoa_id FOREIGN KEY (id) REFERENCES pessoa(id);

ALTER TABLE pessoa_fisica
    ADD CONSTRAINT fk_pessoa_fisica_links_id FOREIGN KEY (links_id) REFERENCES links(id);

ALTER TABLE pessoa_fisica
    ADD CONSTRAINT fk_pessoa_fisica_feedback_id FOREIGN KEY (feedback_id) REFERENCES feedback(id);

ALTER TABLE pessoa_juridica
    ADD CONSTRAINT fk_pessoa_juridica_pessoa_id FOREIGN KEY (id) REFERENCES pessoa(id);

ALTER TABLE pessoa_juridica
    ADD CONSTRAINT fk_pessoa_juridica_endereco_id FOREIGN KEY (id_endereco) REFERENCES endereco(id);

ALTER TABLE telefone
    ADD CONSTRAINT fk_telefone_pessoa_id FOREIGN KEY (pessoa_id) REFERENCES pessoa(id);

ALTER TABLE vaga
    ADD CONSTRAINT fk_vaga_empresa_id FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE pessoa_fisica
    ADD COLUMN data_cadastro date;

ALTER TABLE avaliacao
    ADD COLUMN avaliadora_id BIGINT;

ALTER TABLE avaliacao
    ADD COLUMN anonima BOOLEAN default true;

ALTER TABLE avaliacao
    DROP COLUMN nome_avaliadora;

ALTER TABLE avaliacao
    ADD CONSTRAINT fk_avaliacao_pf FOREIGN KEY (avaliadora_id) REFERENCES pessoa_fisica(id);