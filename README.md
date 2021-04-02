# TECNOMARIAS REST API

URL base: localhost:8080/tecnomarias

## Vaga

Serviço para criação, busca, alteração e remoção de vagas. Uma vaga só pode ser criada por uma `PessoaJuridica` e alterada ou removida pela PJ que a criou. 
A recuperação de vagas é aberta e feita sem precisar de autenticação. É possível filtrar vagas por área de atuação, cargo, localidade ou empresa.

`POST /api/vaga`

### body

```json

```

### repsonse

```json
{
  "id": 4445,
  "stuff": "things"
}
```

`GET /api/vaga/{id}`

Retorna vaga pelo seu ID.

`PUT /api/vaga/{id}`

### body 
```json

```

Altera a vaga com ID informado.

### response

```json

```

`DELETE /api/vaga/{id}`

Remove a vaga com ID informado.

`GET /api/vaga/count`

Retorna o número total de vagas.

`GET /api/vaga?start={start}&pageSize={size}`

| parametro |           descrição           |
|-----------|:-----------------------------:|
| start     |   Número do início da página  |
| pageSize  | Número de itens em uma página |

Retorna todas as vagas com paginação.

`GET /api/vaga/empresa/{id}`

Retorna vagas da empresa com id informado.

`GET /api/vaga/filtro?filtro={filtro}&valor={valor}`

| parametro |                       descrição                      |
|-----------|:----------------------------------------------------:|
| filtro    | Seleciona vagas por areaAtuacao, cargo ou localidade |
| valor     |                    Valor do filtro                   |

Retorna vagas de acordo com um determinado atributo.

### response

```json
[
  {},
  {}
]
```

## PessoaJuridica

`GET /api/pj`

Retorna todas as organizações cadastradas.

`GET /api/pj/{id}`

Retorna uma organização pelo seu ID.

`GET /api/pj/area_atuacao`

Lista as areas de atuação cadastradas.

`POST /api/pj/{id}/avaliacao`

### body
```json
{
  "comentario": "empresa showdi",
  "nota": "4.5",
  "timestamp": "2021-04-01T12:00",
  "nomeAvaliadora": "Ana"
}
```

Cria uma nova avaliacao para uma empresa. Somente usuaria logada pode enviar uma avaliação. Nome da avaliadora é opcional.

## PessoaFisica

`GET /api/pf/{id}`

Necessita autenticação, somente usuários logados podem acessar. Retorna informações de perfil de uma usuária.

## Usuaria

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

## Feedback

Serviço para usuárias comentarem sobre a experiência na plataforma. Comentários serão exibidos na página inicial da aplicação.

`GET /api/feedback`

Endpoint usado pelo frontend para exibir os 3 comentários mais recentes de usuárias sobre a Tecnomarias.

### response

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

`POST api/feedback`

Salva um feedback enviado por uma usuária.

### body

```json
{
  "comentario": "Melhor plataforma para achar vagas.",
  "pessoaId": 2345
}
```

| propriedade |                 descrição                 |
|-------------|:-----------------------------------------:|
| comentario  | Comentário de feedback sobre a plataforma |
| pessoaId    |   ID da pessoa física autora do feedback  |

### response

```json
{
  "id": 7899,
  "comentario": "Melhor plataforma para achar vagas.",
  "pessoaId": 2345
}
```

___

## Rodando aplicação

O projeto possui um wrapper do Maven, portanto não é necessário ter a ferramenta instalada. Para compilar aplicação e gerar um WAR que será deployed em um servidor local, na raíz do projeto usar comando:

```shell
./mvnw clean install
```

O projeto é configurado para rodar em um servidor Glasshfish 4.1.2.