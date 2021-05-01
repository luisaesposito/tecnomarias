-- insere PJs
INSERT INTO public.pessoa(id, tipo_pessoa, email, nome)
VALUES (1, 'PJ', 'contato@ncsports.com.br', 'Noah e Clara Esportes Ltda'),
       (2, 'PJ', 'rh@acme.com.br', 'ACME Co');

INSERT INTO public.endereco (id, bairro, complemento, logradouro, municipio_ibge, numero)
VALUES (3, 'Santa Rosa', null, 'Rua dos Bobos', 3303302, '0'),
       (4, 'Picada', null, 'Rua dos Doidos', 3300209, '1');

INSERT INTO public.pessoa_juridica(id, area_atuacao, cnpj, descricao, porte_empresa, site, id_endereco)
VALUES (1, 'e-commerce', '81898985000116', 'Vendemos artigos esportivos pela internet',
        'EMPRESA_PEQUENO_PORTE', 'www.ncsports.com.br', 3),
       (2, 'publicidade', '21747460000158', 'Agencia de publicidade e propaganda',
        'EMPRESA_MEDIO_PORTE', 'www.acme.com.br', 4);

-- insere vagas
INSERT INTO public.vaga(id, empresa_id, area_atuacao, cargo, descricao, valor, localidade)
VALUES (5, 1, 'analise de dados', 'JUNIOR', 'analisar dados, modelagem', 3500, 'Rio de Janeiro'),
       (6, 1, 'analise de dados', 'ESTAGIARIA', 'analisar dados, modelagem', 2000, 'Rio de Janeiro'),
       (7, 2, 'desenvolvimento de software', 'SENIOR', 'desenvolver aplicações web em java', 5500, 'São Paulo');

-- insere PFs
INSERT INTO PUBLIC.pessoa (id, nome, tipo_pessoa, email)
VALUES (8, 'Graziela de Jesus', 'PF', 'grazielajj@gmail.com'),
       (9, 'Maria Antonieta Da Paz', 'PF', 'mariaantonieta@gmail.com'),
       (10, 'Yasmin Natália Santos', 'PF', 'yasminsantos@gmail.com'),
       (11, 'Analu Camila Débora Ramos', 'PF', 'analuramos@gmail.com');

INSERT INTO PUBLIC.links (id, linked_in, git_hub, portfolio, facebook)
VALUES (12, 'linkedIn.com/grazielajj', 'git_hub.com/grazielajj', 'seuportifolio.com/grazielajj',
        'facebook.com/grazielajj'),
       (13, 'linkedIn.com/mariaantonieta', 'git_hub.com/mariaantonieta', 'seuportifolio.com/mariaantonieta',
        'facebook.com/mariaantonieta'),
       (14, 'linkedIn.com/yasmin-nat', 'git_hub.com/yasmin', 'seuportifolio.com/yasmin-nat', NULL),
       (15, 'linkedIn.com/analu', 'git_hub.com/analu', 'seuportifolio.com/analu', NULL);

INSERT INTO PUBLIC.pessoa_fisica (id, links_id)
VALUES (8, 12),
       (9, 13),
       (10, 14),
       (11, 15);

-- insere feedbacks
INSERT INTO PUBLIC.feedback (id, comentario)
VALUES (16, 'Otimo site, recomendo a todos'),
       (17, 'O site não está perfeito, mas atendeu a minha necessidade'),
       (18, 'Desejo todo o bem aos criadores do site por me proporcionarem a chance de obter um emprego');
--
UPDATE PUBLIC.pessoa_fisica SET feedback_id = 16 WHERE id = 8;
UPDATE PUBLIC.pessoa_fisica SET feedback_id = 17 WHERE id = 9;
UPDATE PUBLIC.pessoa_fisica SET feedback_id = 18 WHERE id = 10;

-- insere avaliacoes
INSERT INTO public.avaliacao(id, id_empresa, comentario, data, nome_avaliadora, nota)
VALUES
(19, 1, 'Ambiente muito saudável', '2021-04-28T00:24:25.153895', 'Graziela de Jesus', 5.0),
(20, 1, 'Amo esse lugar', '2021-04-28T00:25:18.551456', 'Juliana', 4.0),
(21, 1, 'Nenhum salário vale a pena quando a pressão é alta...', '2021-02-28T08:25:18.551456', NULL, 3.5);