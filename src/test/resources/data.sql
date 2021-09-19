-- insere PJs
INSERT INTO pessoa(id, tipo_pessoa, email, nome)
VALUES (1, 'PJ', 'contato@ncsports.com.br', 'Noah e Clara Esportes Ltda'),
       (2, 'PJ', 'rh@acme.com.br', 'ACME Co'),
       (30, 'PJ', 'contato@primeup.com.br', 'PrimeUP'),
       (101, 'PJ', 'rh@boticario.com.br', 'Boticario');

INSERT INTO endereco (id, bairro, complemento, logradouro, municipio_ibge, numero)
VALUES (3, 'Santa Rosa', null, 'Rua dos Bobos', 3303302, '0'),
       (4, 'Picada', null, 'Rua dos Doidos', 3300209, '1');

INSERT INTO pessoa_juridica(id, area_atuacao, cnpj, descricao, porte_empresa, site, id_endereco)
VALUES (1, 'e-commerce', '81898985000116', 'Vendemos artigos esportivos pela internet',
        'EMPRESA_PEQUENO_PORTE', 'www.ncsports.com.br', 3),
       (2, 'publicidade', '21747460000158', 'Agencia de publicidade e propaganda',
        'EMPRESA_MEDIO_PORTE', 'www.acme.com.br', 4),
       (30, 'TI', '32377406000184', 'PrimeUP Consultoria', 'EMPRESA_MEDIO_PORTE', 'www.primeup.com.br', null),
       (101, 'Perfumaria', '43997056000175', 'Boticario Perfumaria', 'EMPRESA_MEDIO_PORTE', 'www.boticario.com.br',
        null);

-- insere vagas
INSERT INTO vaga(id, empresa_id, area_atuacao, cargo, descricao, valor, localidade)
VALUES (5, 1, 'analise de dados', 'JUNIOR', 'analise dados, modelagem', 3500, 'Rio de Janeiro'),
       (6, 1, 'analise de dados', 'ESTAGIARIA', 'analise dados, modelagem', 2000, 'Rio de Janeiro'),
       (7, 2, 'desenvolvimento de software', 'PLENO', 'desenvolver aplicações web em java', 5500, 'São Paulo'),
       (31, 2, 'analista de sistema', 'PLENO', 'desenvolver aplicações web em php', 5500, 'São Paulo'),
       (102, 2, 'project manager', 'SENIOR', 'gerir os projeto tudo', 8500, 'São Paulo');

-- insere PFs
INSERT INTO pessoa (id, nome, tipo_pessoa, email)
VALUES (8, 'Graziela de Jesus', 'PF', 'grazielajj@gmail.com'),
       (9, 'Maria Antonieta Da Paz', 'PF', 'mariaantonieta@gmail.com'),
       (10, 'Yasmin Natália Santos', 'PF', 'yasminsantos@gmail.com'),
       (11, 'Analu Camila Débora Ramos', 'PF', 'analuramos@gmail.com'),
       (50, 'Duda Martins', 'PF', 'duda@gmail.com'),
       (103, 'Maraia Querei', 'PF', 'maraiaquerei@gmail.com'),
       (106, 'Fala Muito', 'PF', 'falamuito@gmail.com');

INSERT INTO links (id, linked_in, git_hub, portfolio, facebook)
VALUES (12, 'linkedIn.com/grazielajj', 'git_hub.com/grazielajj', 'seuportifolio.com/grazielajj',
        'facebook.com/grazielajj'),
       (13, 'linkedIn.com/mariaantonieta', 'git_hub.com/mariaantonieta', 'seuportifolio.com/mariaantonieta',
        'facebook.com/mariaantonieta'),
       (14, 'linkedIn.com/yasmin-nat', 'git_hub.com/yasmin', 'seuportifolio.com/yasmin-nat', NULL),
       (15, 'linkedIn.com/analu', 'git_hub.com/analu', 'seuportifolio.com/analu', NULL),
       (51, 'linkedIn.com/duda', null, null, NULL),
       (52, 'linkedIn.com/maraia', null, null, NULL),
       (54, 'linkedIn.com/falafala', null, null, NULL);

INSERT INTO pessoa_fisica (id, links_id, data_cadastro)
VALUES (8, 12, {ts '2020-01-01'}),
       (9, 13, {ts '2018-09-10'}),
       (10, 14, {ts '2019-10-22'}),
       (11, 15, {ts '2021-02-15'}),
       (50, 51, CURRENT_DATE),
       (103, 52, CURRENT_DATE),
       (106, 54, CURRENT_DATE);


-- insere feedbacks
INSERT INTO feedback (id, pessoa_fisica_id, comentario)
VALUES (16, 8, 'Otimo site, recomendo a todos'),
       (17, 9, 'O site não está perfeito, mas atendeu a minha necessidade'),
       (18, 10, 'Desejo todo o bem aos criadores do site por me proporcionarem a chance de obter um emprego'),
       (104, 103, 'Os criadores são as melhores pessoas do planeta'),
       (107, 106, 'Gosto de falar mal das coisas');

--
UPDATE pessoa_fisica
SET feedback_id = 16
WHERE id = 8;
UPDATE pessoa_fisica
SET feedback_id = 17
WHERE id = 9;
UPDATE pessoa_fisica
SET feedback_id = 18
WHERE id = 10;
UPDATE pessoa_fisica
SET feedback_id = 104
WHERE id = 103;
UPDATE pessoa_fisica
SET feedback_id = 107
WHERE id = 106;

-- insere avaliacoes
INSERT INTO avaliacao(id, id_empresa, comentario, data, avaliadora_id, anonima, nota)
VALUES (19, 1, 'Ambiente muito saudável', '2021-04-28T00:24:25.153895', 8, true, 5),
       (20, 1, 'Amo esse lugar', '2021-04-28T00:25:18.551456', 10, false, 4),
       (21, 1, 'Nenhum salário vale a pena quando a pressão é alta...', '2021-02-28T08:25:18.551456', 11, true, 3),
       (105, 2, 'Esse lugar me dá nauseas.', '2021-02-28T08:25:18.551456', 50, true, 1);