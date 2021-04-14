-- insere PJs
INSERT INTO public.pessoa(id, tipo_pessoa, email, nome) VALUES
(8000,'PJ','teste@id.uff.br', 'Noah e Clara Esportes Ltda'),
(8080,'PJ','administracao@ep.com.br', 'Noah e Clara Esportes Ltda');

INSERT INTO public.pessoa_juridica(id, area_atuacao, cnpj, descricao, porte_empresa, site) VALUES
(8000, 'e-commerce', '81898985000116', 'Vendemos artigos esportivos pela internet',
 'EMRPESA_PEQUENO_PORTE', 'www.noaheclaraesportesltda.com.br'),
(8080, 'publicidade', '21747460000158', 'Vendemos artigos esportivos pela internet',
 'EMPRESA_MEDIO_PORTE', 'www.noaheclaraesportesltda.com.br');

-- insere vagas
INSERT INTO public.vaga(id, id_empresa, area_atuacao, cargo, descricao, valor, localidade) VALUES
(8010, 8000, 'analise de dados', 'JUNIOR', 'analisar dados, modelagem', 3500, 'Rio de Janeiro'),
(8011, 8000, 'analise de dados', 'ESTAGIARIA', 'analisar dados, modelagem', 2000, 'Rio de Janeiro'),
(8020, 8080, 'desenvolvimento de software', 'SENIOR', 'desenvolver aplicações web em java', 5500, 'São Paulo');

-- insere PFs
INSERT INTO PUBLIC.pessoa (id,nome,tipo_pessoa,email) VALUES
(9000, 'Graziela de Jesus', 'PF', 'grazielajj@gmail.com'),
(9010, 'Maria Antonieta Da Paz', 'PF', 'mariaantonieta@gmail.com'),
(9020, 'Yasmin Natália Santos', 'PF', 'yasminsantos@gmail.com'),
(9030, 'Analu Camila Débora Ramos', 'PF', 'analuramos@gmail.com');

INSERT INTO PUBLIC.links (id,linkedin, github, portfolio, facebook) VALUES
(9001,'linkedIn.com/grazielajj','github.com/grazielajj','seuportifolio.com/grazielajj','facebook.com/grazielajj'),
(9011,'linkedIn.com/mariaantonieta','github.com/mariaantonieta','seuportifolio.com/mariaantonieta','facebook.com/mariaantonieta'),
(9021,'linkedIn.com/yasmin-nat','github.com/yasmin','seuportifolio.com/yasmin-nat', NULL),
(9031,'linkedIn.com/analu','github.com/analu','seuportifolio.com/analu', NULL);

INSERT INTO PUBLIC.pessoa_fisica (id,links_id) VALUES
(9000,9001), (9010,9011), (9020,9021), (9030, 9031);

-- insere feedbacks
INSERT INTO PUBLIC.feedback (id, comentario, pessoa_fisica_id) VALUES
(9050, 'Otimo site, recomendo a todos', 9000),
(9060, 'O site não está perfeito, mas atendeu a minha necessidade', 9010),
(9070, 'Desejo todo o bem aos criadores do site por me proporcionarem a chance de obter um emprego', 9020);