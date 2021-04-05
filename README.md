# TECNOMARIAS REST API

URL base: `localhost:8080/tecnomarias`

## Vaga

Serviço para criação, busca, alteração e remoção de vagas. Uma vaga só pode ser criada por uma `PessoaJuridica` e alterada ou removida pela PJ que a criou. 
A recuperação de vagas é aberta e feita sem precisar de autenticação. É possível filtrar vagas por área de atuação, cargo, localidade ou empresa.

### Criar vaga

`POST /api/vaga` cria uma nova vaga associada a uma PJ válida, pelo idEmpresa.

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

`200 OK`

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

`GET /api/vaga/{id}` Retorna vaga pelo seu ID.

#### Exemplo

`GET /api/vaga/4431`

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

#### Exemplo

`GET /api/vaga/888888`

#### Response

`404 BAD_REQUEST` vaga não encontrada

### Alterar vaga

`PUT /api/vaga/{id}` altera a vaga com ID informado

#### Exemplo

`PUT /api/vaga/4431`

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

`DELETE /api/vaga/{id}`

Remove a vaga com ID informado.

### Count vagas

`GET /api/vaga/count`

Retorna o número total de vagas.

### Todas as vagas paginado (em breve)

`GET /api/vaga?start={start}&pageSize={size}`

| parametro |           descrição           |
|-----------|:-----------------------------:|
| start     |   Número do início da página  |
| pageSize  | Número de itens em uma página |

Retorna todas as vagas com paginação.

`GET /api/vaga/empresa/{id}`

Retorna vagas da empresa com id informado.

### Filtrar vagas

`GET /api/vaga/filtro?filtro={filtro}&valor={valor}`

| parametro |                       descrição                      |
|-----------|:----------------------------------------------------:|
| filtro    | Seleciona vagas por areaAtuacao, cargo ou localidade |
| valor     |                    Valor do filtro                   |

Retorna vagas de acordo com um determinado atributo.

#### response

```json
[
  {},
  {}
]
```

## Empresa (PessoaJuridica)

### Listar empresas

`GET /api/pj` Retorna todas as organizações cadastradas.

```json

```

### Buscar empresa

`GET /api/pj/{id}` Retorna uma organização pelo seu ID.

### Listar area de atuação

`GET /api/pj/area_atuacao` Lista as areas de atuação cadastradas.

### Avaliar empresa

`POST /api/pj/{id}/avaliacao` Cria uma nova avaliacao para uma empresa. Somente usuaria logada pode enviar uma avaliação. Nome da avaliadora é opcional.

#### Body

```json

```

#### Response

`200 OK` empresa avaliada com sucesso

```json

```

## PessoaFisica

### Buscar PF por id

`GET /api/pf/{id}` Retorna informações de perfil de uma usuária.

Necessita autenticação, somente usuários logados podem acessar. (em breve)

#### Response

```json

```

### Salvar uma PF

`POST /api/pf` cria uma nova PF, com a lista de links que serão exibidos no perfil

#### Body

```json

```

#### Response

```json

```

## Feedback

Serviço para usuárias comentarem sobre a experiência na plataforma. Comentários serão exibidos na página inicial da aplicação.

### Buscar feedbacks

`GET /api/feedback` Endpoint usado pelo frontend para exibir os 3 comentários mais recentes de usuárias sobre a Tecnomarias.

#### Response

```json
[
  {
    "id": 7899,
    "comentario": "Melhor plataforma para achar vagas.",
    "pessoaId": 2345
  },
  {
    "id": 7923,
    "comentario": "Incrivel.",
    "pessoaId": 1234
  },
  {
    "id": 7930,
    "comentario": "Gostei.",
    "pessoaId": 7756
  }
]
```

### Enviar feedback

`POST api/feedback`  Salva um feedback enviado por uma usuária.

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

`POST /user`

Cria um novo usuário.

`GET /user/{id}`

Necessita autenticação. Retorna todas as informações de uma usuária.

`POST /user/perfil?tipo={tipo}`

Necessita autenticação. Cria um perfil de usuário, do tipo PF ou PJ.

`PUT /user/{id}`

Necessita autenticação. Altera as informações de uma usuária.

`DELETE /user/{id}`

Necessita autenticação. Remove uma usuária.

___

## Rodando a aplicação

O projeto possui um wrapper do Maven, portanto não é necessário ter a ferramenta instalada. Para compilar aplicação e gerar um WAR que será deployed em um servidor local, na raíz do projeto usar comando:

```shell
./mvnw clean install
```

O projeto é configurado para rodar em um servidor Glasshfish 4.1.2.