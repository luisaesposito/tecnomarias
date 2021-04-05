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
