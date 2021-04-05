INSERT INTO public.pessoa(id, email, nome) VALUES (1, 'teste@id.uff.br', 'teste');
INSERT INTO public.pessoajuridica(id, areaatuacao, cnpj, descricao, porteempresa) VALUES (1, 'cosmeticos', '36485199000132', 'cd', 'MICROEMPRESA');

INSERT INTO PUBLIC.pessoa (id,nome,email) values(11, 'Graziela de Jesus', 'grazielajj@gmail.com');
INSERT INTO PUBLIC.links (id,linkedin, github, portifolio, facebook) values (11,'linkedIn.com/grazielajj','github.com/grazielajj','seuportifolio.com/grazielajj','facebook.com/grazielajj');
INSERT INTO PUBLIC.pessoafisica (id,links_id) values(11,11);

INSERT INTO PUBLIC.pessoa (id,nome,email) values(12, 'Maria Antonieta Da Paz', 'mariaantonieta@gmail.com');
INSERT INTO PUBLIC.links (id,linkedin, github, portifolio, facebook) values (11,'linkedIn.com/mariaantonieta','github.com/mariaantonieta','seuportifolio.com/mariaantonieta','facebook.com/mariaantonieta');
INSERT INTO PUBLIC.pessoafisica (id,links_id) values(12,12);

INSERT INTO PUBLIC.pessoa (id,nome,email) values(13, 'Estrela da Silva', 'estrela@gmail.com');
INSERT INTO PUBLIC.links (id,linkedin, github, portifolio, facebook) values (13,'linkedIn.com/estrela','github.com/estrela','seuportifolio.com/estrela','facebook.com/estrela');
INSERT INTO PUBLIC.pessoafisica (id,links_id) values(13,13);


INSERT INTO PUBLIC.feedback (id, comentario, pessoafisica_id) values (1, 'Otimo site, recomendo a todos', 13);
INSERT INTO PUBLIC.feedback (id, comentario, pessoafisica_id) values (2, 'O site não está perfeito, mas atendeu a minha necessidade', 12);
INSERT INTO PUBLIC.feedback (id, comentario, pessoafisica_id) values (3, 'Desejo todo o bem aos criadores do site por me proporcionarem a chance de obter um emprego', 11);