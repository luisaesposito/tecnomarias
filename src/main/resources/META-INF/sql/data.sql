INSERT INTO public.pessoa(id, email, nome) VALUES (1, 'teste@id.uff.br', 'teste');
INSERT INTO public.pessoajuridica(id, areaatuacao, cnpj, descricao, porteempresa) VALUES (1, 'cosmeticos', '36485199000132', 'cd', 'MICROEMPRESA');

INSERT INTO PUBLIC.pessoa (id,nome,email) values(11, 'Joao Augusto Ferreira', 'joaoaugusto@gmail.com')
    INSERT INTO PUBLIC.links (id,linkedin, github, portifolio, facebook) values (11,'linkedIn.com/joaoausgusto','github.com/joaoausgusto','seuportifolio.com/joaoausgusto','facebook.com/joaoausgusto')
INSERT INTO PUBLIC.pessoafisica (id,links_id) values(11,11)

INSERT INTO PUBLIC.pessoa (id,nome,email) values(12, 'Maria Antonieta Da Paz', 'mariaantonieta@gmail.com')
INSERT INTO PUBLIC.links (id,linkedin, github, portifolio, facebook) values (11,'linkedIn.com/mariaantonieta','github.com/mariaantonieta','seuportifolio.com/mariaantonieta','facebook.com/mariaantonieta')
INSERT INTO PUBLIC.pessoafisica (id,links_id) values(12,12)

INSERT INTO PUBLIC.pessoa (id,nome,email) values(13, 'Enzo Estrela da Silva', 'enzoestrela@gmail.com')
INSERT INTO PUBLIC.links (id,linkedin, github, portifolio, facebook) values (13,'linkedIn.com/enzoestrela','github.com/enzoestrela','seuportifolio.com/enzoestrela','facebook.com/enzoestrela')
INSERT INTO PUBLIC.pessoafisica (id,links_id) values(13,13)