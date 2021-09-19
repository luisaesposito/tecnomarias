ALTER SEQUENCE hibernate_sequence RESTART 200 INCREMENT 1;

TRUNCATE public.pessoa CASCADE;
TRUNCATE public.endereco CASCADE;
TRUNCATE public.links CASCADE;

ALTER TABLE public.pessoa_fisica
    ADD COLUMN data_cadastro date;

ALTER TABLE public.avaliacao
    ADD COLUMN avaliadora_id BIGINT,
    ADD COLUMN anonima BOOLEAN default true;

ALTER TABLE public.avaliacao
    DROP COLUMN nome_avaliadora;

ALTER TABLE ONLY public.avaliacao
    ADD CONSTRAINT fk_avaliacao_pf FOREIGN KEY (avaliadora_id) REFERENCES public.pessoa_fisica(id);