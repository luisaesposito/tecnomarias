-- insere PJs
INSERT INTO public.pessoa(id, tipo_pessoa, email, nome)
    VALUES (1,'PJ','judith@', 'financeiro@fabioemarliinformaticame.com.br', 'Fábio e Marli Informática ME');
INSERT INTO public.pessoajuridica(id, areaatuacao, cnpj, descricao, porteempresa, site)
    VALUES (1, 'serviços informatica', '29533645000171', 'Somo uma microempresa de suporte ténico de informatica',
            'MICROEMPRESA', 'www.fabioemarliinformaticame.com.br');
INSERT INTO public.pessoa(id, tipo_pessoa, email, nome)
    VALUES (2,'PJ','teste@id.uff.br', 'Noah e Clara Esportes Ltda');
INSERT INTO public.pessoajuridica(id, areaatuacao, cnpj, descricao, porteempresa, site)
    VALUES (2, 'e-commerce', '81898985000116', 'Vendemos artigos esportivos pela internet',
            'EMRPESA_PEQUENO_PORTE', 'www.noaheclaraesportesltda.com.br');

-- insere vagas
INSERT INTO public.vaga(id, id_empresa, areaatuacao, cargo, descricao, valor, localidade) VALUES
    (3, 2, 'analise de dados', 'JUNIOR', 'analisar dados, modelagem', 3500, 'Rio de Janeiro'),
    (4, 2, 'analise de dados', 'ESTAGIARIA', 'analisar dados, modelagem', 2000, 'Rio de Janeiro'),
    (5, 1, 'desenvolvimento de software', 'SENIOR', 'desenvolver aplicações web em java', 5500, 'São Paulo');

-- insere PFs
INSERT INTO PUBLIC.pessoa (id,nome,tipo_pessoa,email) values(11, 'Graziela de Jesus','PF', 'grazielajj@gmail.com');
INSERT INTO PUBLIC.links (id,linkedin, github, portifolio, facebook) values (11,'linkedIn.com/grazielajj','github.com/grazielajj','seuportifolio.com/grazielajj','facebook.com/grazielajj');
INSERT INTO PUBLIC.pessoafisica (id,links_id) values(11,11);

INSERT INTO PUBLIC.pessoa (id,nome,tipo_pessoa, email) values(12, 'Maria Antonieta Da Paz','PF', 'mariaantonieta@gmail.com');
INSERT INTO PUBLIC.links (id,linkedin, github, portifolio, facebook) values (11,'linkedIn.com/mariaantonieta','github.com/mariaantonieta','seuportifolio.com/mariaantonieta','facebook.com/mariaantonieta');
INSERT INTO PUBLIC.pessoafisica (id,links_id) values(12,12);

INSERT INTO PUBLIC.pessoa (id,nome,tipo_pessoa,email) values(13, 'Estrela da Silva','PF', 'estrela@gmail.com');
INSERT INTO PUBLIC.links (id,linkedin, github, portifolio, facebook) values (13,'linkedIn.com/estrela','github.com/estrela','seuportifolio.com/estrela','facebook.com/estrela');
INSERT INTO PUBLIC.pessoafisica (id,links_id) values(13,13);

-- insere feedbacks
INSERT INTO PUBLIC.feedback (id, comentario, pessoafisica_id) values (1, 'Otimo site, recomendo a todos', 13);
INSERT INTO PUBLIC.feedback (id, comentario, pessoafisica_id) values (2, 'O site não está perfeito, mas atendeu a minha necessidade', 12);
INSERT INTO PUBLIC.feedback (id, comentario, pessoafisica_id) values (3, 'Desejo todo o bem aos criadores do site por me proporcionarem a chance de obter um emprego', 11);