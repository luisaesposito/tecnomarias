CREATE SEQUENCE hibernate_sequence START 20 INCREMENT 1;

CREATE TABLE public.avaliacao (
    id bigint NOT NULL,
    comentario character varying(500) NOT NULL,
    nome_avaliadora character varying(255),
    nota double precision NOT NULL,
    data TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_empresa bigint
);

CREATE TABLE public.endereco (
    id bigint NOT NULL,
    bairro character varying(50),
    complemento character varying(50),
    logradouro character varying(255) NOT NULL,
    municipio_ibge integer NOT NULL,
    numero character varying(10) NOT NULL
);

CREATE TABLE public.feedback (
    id bigint NOT NULL,
    comentario character varying(500) NOT NULL
);

CREATE TABLE public.links (
    id bigint NOT NULL,
    facebook character varying(255),
    git_hub character varying(255),
    linked_in character varying(255),
    portfolio character varying(255)
);

CREATE TABLE public.pessoa (
    id bigint NOT NULL,
    tipo_pessoa character varying(31),
    email character varying(100),
    nome character varying(255)
);

CREATE TABLE public.pessoa_fisica (
    id bigint NOT NULL,
    feedback_id bigint,
    links_id bigint
);

CREATE TABLE public.pessoa_juridica (
    id bigint NOT NULL,
    area_atuacao character varying(100),
    cnpj character varying(14),
    descricao character varying(255),
    media_avaliacao double precision,
    porte_empresa character varying(255),
    site character varying(255),
    id_endereco bigint
);

CREATE TABLE public.telefone (
    id bigint NOT NULL,
    ddd character varying(2),
    ddi character varying(3),
    numero character varying(10),
    id_pessoa bigint
);

CREATE TABLE public.vaga (
    id bigint NOT NULL,
    area_atuacao character varying(100) NOT NULL,
    cargo character varying(50) NOT NULL,
    descricao character varying(255) NOT NULL,
    localidade character varying(100),
    valor double precision,
    id_empresa bigint
);

ALTER TABLE ONLY public.avaliacao
    ADD CONSTRAINT avaliacao_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.links
    ADD CONSTRAINT links_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.pessoa_fisica
    ADD CONSTRAINT pessoa_fisica_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_cnpj_key UNIQUE (cnpj);

ALTER TABLE ONLY public.pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT telefone_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.vaga
    ADD CONSTRAINT vaga_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.avaliacao
    ADD CONSTRAINT fk_avaliacao_pessoa_juridica_id FOREIGN KEY (id_empresa) REFERENCES public.pessoa_juridica(id);

ALTER TABLE ONLY public.pessoa_fisica
    ADD CONSTRAINT fk_pessoa_fisica_pessoa_id FOREIGN KEY (id) REFERENCES public.pessoa(id);

ALTER TABLE ONLY public.pessoa_fisica
    ADD CONSTRAINT fk_pessoa_fisica_links_id FOREIGN KEY (links_id) REFERENCES public.links(id);

ALTER TABLE ONLY public.pessoa_fisica
    ADD CONSTRAINT fk_pessoa_fisica_feedback_id FOREIGN KEY (feedback_id) REFERENCES public.feedback(id);

ALTER TABLE ONLY public.pessoa_juridica
    ADD CONSTRAINT fk_pessoa_juridica_pessoa_id FOREIGN KEY (id) REFERENCES public.pessoa(id);

ALTER TABLE ONLY public.pessoa_juridica
    ADD CONSTRAINT fk_pessoa_juridica_endereco_id FOREIGN KEY (id_endereco) REFERENCES public.endereco(id);

ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_telefone_pessoa_id FOREIGN KEY (id_pessoa) REFERENCES public.pessoa(id);

ALTER TABLE ONLY public.vaga
    ADD CONSTRAINT fk_vaga_empresa_id FOREIGN KEY (id_empresa) REFERENCES public.pessoa_juridica(id);
