# TECNOMARIAS REST API


## Rodando a aplicação

O projeto possui um wrapper do Maven, portanto não é necessário ter a ferramenta instalada. É necessário uma conexão com banco PostgreSQL, com uma base nomeada `tecnomarias`. Para rodar a aplicação localmente, na raíz do projeto usar comando:

```shell
./mvnw spring-boot:run
```

URL local:`localhost:8080/`\
URL do Heroku: `http://tecnomarias.herokuapp.com/`

## Vaga

Serviço para criação, busca, alteração e remoção de vagas. Uma vaga só pode ser criada por uma `PessoaJuridica` e alterada ou removida pela PJ que a criou. 
A recuperação de vagas é aberta e feita sem precisar de autenticação. É possível filtrar vagas por área de atuação, cargo, localidade ou empresa.

### Criar vaga

`POST /api/v1/vaga` cria uma nova vaga associada a uma PJ válida, pelo idEmpresa.

#### Exemplo 1

#### Body

```json
{
  "idEmpresa": 234,
  "areaAtuacao": "analise de dados",
  "cargo": "JUNIOR",
  "descricao": "Descrição da vaga",
  "valor": 3500
}
```

#### Response

`200 OK` sucesso

```json
{
  "id": 4431,
  "idEmpresa": 234,
  "areaAtuacao": "analise de dados",
  "cargo": "JUNIOR",
  "descricao": "Descrição da vaga",
  "valor": 3500
}
```

#### Exemplo 2

#### Body

```json
{
  "idEmpresa": 0,
  "areaAtuacao": "analise de dados",
  "cargo": "JUNIOR",
  "descricao": "Descrição da vaga",
  "valor": 3500
}
```

#### Response

`400 BAD_REQUEST` empresa inválida

### Buscar vaga

`GET /api/v1/vaga/{id}` Retorna vaga pelo seu ID.

#### Exemplo 1

`GET /api/v1/vaga/4431`

#### Response

`200 OK` sucesso

```json
{
  "id": 4431,
  "idEmpresa": 234,
  "areaAtuacao": "analise de dados",
  "cargo": "JUNIOR",
  "descricao": "Descrição da vaga",
  "valor": 3500
}
```

#### Exemplo 2

`GET /api/v1/vaga/888888`

#### Response

`404 BAD_REQUEST` vaga não encontrada

### Alterar vaga

`PUT /api/v1/vaga/{id}` altera a vaga com ID informado

#### Exemplo

`PUT /api/v1/vaga/4431`

#### Body

```json
{
  "idEmpresa": 234,
  "areaAtuacao": "ANALISTA DE DADOS",
  "cargo": "JUNIOR",
  "descricao": "Descrição da vaga",
  "valor": 3500
}
```

#### Response

`200 OK` sucesso

```json
{
  "id": 4431,
  "idEmpresa": 234,
  "areaAtuacao": "ANALISTA DE DADOS",
  "cargo": "JUNIOR",
  "descricao": "Descrição da vaga",
  "valor": 3500
}
```

### Remover Vaga

`DELETE /api/v1/vaga/{id}`

Remove a vaga com ID informado.

#### Exemplo

`DELETE /api/v1/vaga/4431`

#### Response

`200 OK` Vaga removida com sucesso.


### Count vagas

`GET /api/v1/vaga/count`

Retorna o número total de vagas.

#### Exemplo

`GET /api/v1/vaga/count`

#### Response

`200 OK` 5

### Todas as vagas paginado (em breve)

`GET /api/v1/vaga?start={start}&pageSize={size}`

| parametro |           descrição           |
|-----------|:-----------------------------:|
| start     |   Número do início da página  |
| pageSize  | Número de itens em uma página |

Retorna todas as vagas com paginação.

###
### Todas as vagas de uma empresa

`GET /api/v1/vaga/empresa/{id}` Retorna vagas da empresa com id informado.

#### Exemplo

`GET /api/v1/vaga/empresa/2`

#### Response

`200 OK` sucesso

```json
[
  {
    "id": 4,
    "idEmpresa": 2,
    "areaAtuacao": "analise de dados",
    "cargo": "ESTAGIARIA",
    "descricao": "analisar dados, modelagem",
    "valor": 2000.0
  },
  {
    "id": 3,
    "idEmpresa": 2,
    "areaAtuacao": "analise de dados",
    "cargo": "JUNIOR",
    "descricao": "quero cafeeeee",
    "valor": 3500.0
  }
]
```



### Filtrar vagas

`GET /api/v1/vaga/filtro?filtro={filtro}&valor={valor}`

| parametro |                       descrição                      |
|-----------|:----------------------------------------------------:|
| filtro    | Seleciona vagas por areaAtuacao, cargo ou localidade |
| valor     |                    Valor do filtro                   |

Retorna vagas de acordo com um determinado atributo.

#### Exemplo

`GET /api/v1/vaga/filtro?filtro=areaAtuacao&valor=analise de dados`

#### Response

```json
[
  {
    "id": 123,
    "idEmpresa": 12312,
    "areaAtuacao": "analise de dados",
    "cargo": "ESTAGIARIA",
    "descricao": "analisar dados, modelagem",
    "valor": 2000.0
  },
  {
    "id": 124,
    "idEmpresa": 12312,
    "areaAtuacao": "analise de dados",
    "cargo": "JUNIOR",
    "descricao": "quero cafeeeee",
    "valor": 3500.0
  }
]
```

## Empresa (PessoaJuridica)

### Listar empresas

`GET /api/v1/pj` Retorna todas as organizações cadastradas.

#### Response

`200 OK` sucesso

```json
[
  {
    "nome": "Fabio e Marli Informatica ME",
    "email": "financeiro@fabioemarliinformaticame.com.br",
    "telefoneList": [],
    "id": 3,
    "cnpj": "48439639000102",
    "site": "www.fabioemarliinformaticame.com.br",
    "descricao": "Somos uma microempresa de suporte tecnico de informatica",
    "porteEmpresa": "MICROEMPRESA",
    "areaAtuacao": "servicos de informatica",
    "avaliacoes": null,
    "mediaAvaliacao": null,
    "endereco": null
  }
]
```

### Buscar empresa

`GET /api/v1/pj/{id}` Retorna uma organização pelo seu ID.

#### Exemplo

`GET /api/v1/pj/2`

#### Response

`200 OK` sucesso

```json
{
    "nome": "Noah e Clara Esportes Ltda",
    "email": "teste@id.uff.br",
    "telefoneList": [],
    "id": 2,
    "cnpj": "81898985000116",
    "site": "www.noaheclaraesportesltda.com.br",
    "descricao": "Vendemos artigos esportivos pela internet",
    "porteEmpresa": "EMPRESA_PEQUENO_PORTE",
    "areaAtuacao": "e-commerce",
    "avaliacoes": [
        {
            "id": 2,
            "comentario": "gostei mas podia ser melhor",
            "nota": 3.0,
            "timestamp": "2021-04-03T12:00",
            "nomeAvaliadora": "Lorem Ipsum",
            "idEmpresa": 2
        }
    ],
    "mediaAvaliacao": null,
    "endereco": null
}
```

### Criar empresa

`POST /api/v1/pj` cria uma nova PJ

#### Exemplo

#### Body
```json
{
    "cnpj": "20549823000188",
    "areaAtuacao": "Ciencia",
    "porteEmpresa": "MICROEMPRESA",
    "descricao":"Grande empresa que se trabalho com experiencias sapecas", 
    "nome": "Sapeciencias",
    "email": "sapeciencias@id.uff.br"
}
```
#### Response

`200 OK` sucesso

```json
{
    "nome": "Sapeciencias",
    "email": "sapeciencias@id.uff.br",
    "telefoneList": [],
    "id": 4,
    "cnpj": "20549823000188",
    "site": null,
    "descricao": "Grande empresa que se trabalho com experiencias sapecas",
    "porteEmpresa": "MICROEMPRESA",
    "areaAtuacao": "Ciencia",
    "avaliacoes": null,
    "mediaAvaliacao": null,
    "endereco": null
}
```


### Listar area de atuação

`GET /api/v1/pj/area_atuacao` Lista as areas de atuação cadastradas.

### Avaliar empresa

`POST /api/v1/pj/{id}/avaliacao` Cria uma nova avaliacao para uma empresa. Somente usuaria logada pode enviar uma avaliação. Nome da avaliadora é opcional.

#### Exemplo

`POST /api/v1/pj/2/avaliacao`

#### Body

```json
{
    "nomeAvaliadora": "Lorem Ipsum",
    "nota": 3,
    "comentario": "gostei mas podia ser melhor"
}
```

#### Response

`200 OK` empresa avaliada com sucesso

```json
{
    "id": 5,
    "comentario": "gostei mas podia ser melhor",
    "nota": 3.0,
    "timestamp": "2021-04-03T12:00",
    "nomeAvaliadora": "Lorem Ipsum",
    "idEmpresa": 2
}
```

## PessoaFisica

### Buscar PF por id

`GET /api/v1/pf/{id}` Retorna informações de perfil de uma usuária.

Necessita autenticação, somente usuários logados podem acessar. (em breve)

#### Exemplo

`GET /api/v1/pf/13`

#### Response

`200 OK` empresa avaliada com sucesso

```json
{
    "nome": "Estrela da Silva",
    "email": "estrela@gmail.com",
    "telefoneList": [],
    "links": {
        "id": 13,
        "linkedIn": "linkedIn.com/estrela",
        "gitHub": "github.com/estrela",
        "portfolio": "seuportifolio.com/estrela",
        "facebook": "facebook.com/estrela"
    }
}
```

### Salvar uma PF

`POST /api/v1/pf` cria uma nova PF, com a lista de links que serão exibidos no perfil

#### Exemplo

#### Body

```json
{
  "nome" : "Lorem Ipsum",
  "links" : {
    "gitHub": "http://www.github.com/loremipsum"
  },
  "email": "loremipsum@id.uff.br"
}
```

#### Response

`200 OK` sucesso

```json
{
    "nome": "Lorem Ipsum",
    "email": "loremipsum@id.uff.br",
    "telefoneList": [],
    "links": {
        "id": 7,
        "linkedIn": null,
        "gitHub": "http://www.github.com/loremipsum",
        "portfolio": null,
        "facebook": null
    }
}
```

## Feedback

Serviço para usuárias comentarem sobre a experiência na plataforma. Comentários serão exibidos na página inicial da aplicação.

### Buscar feedbacks

`GET /api/v1/feedback` Endpoint usado pelo frontend para exibir os 3 comentários mais recentes de usuárias sobre a Tecnomarias.

#### Exemplo

#### Response

```json
[
  {
    "id": 3,
    "comentario": "Desejo todo o bem aos criadores do site por me proporcionarem a chance de obter um emprego",
    "pessoa": {
      "id": 11,
      "nome": "Graziela de Jesus",
      "email": "grazielajj@gmail.com",
      "links": {
        "id": 11,
        "linkedIn": "linkedIn.com/grazielajj",
        "gitHub": "github.com/grazielajj",
        "portfolio": "seuportifolio.com/grazielajj",
        "facebook": "facebook.com/grazielajj"
      },
      "telefones": []
    }
  }
]
```

### Enviar feedback

`POST api/v1/feedback`  Salva um feedback enviado por uma usuária.

### body

```json
{
  "comentario": "Melhor plataforma para achar vagas.",
  "pessoaId": 2345
}
```

### response

```json
{
  "id": 7899,
  "comentario": "Melhor plataforma para achar vagas.",
  "pessoaId": 2345
}
```

## Usuaria (em breve)

`POST api/v1/user`

Cria um novo usuário.

`GET api/v1/user/{id}`

Necessita autenticação. Retorna todas as informações de uma usuária.

`POST api/v1/user/perfil?tipo={tipo}`

Necessita autenticação. Cria um perfil de usuário, do tipo PF ou PJ.

`PUT api/v1/user/{id}`

Necessita autenticação. Altera as informações de uma usuária.

`DELETE api/v1/user/{id}`

Necessita autenticação. Remove uma usuária.
