# TECNOMARIAS REST API

Aplicação Web que utiliza framework Sping-Boot 2.4 com JDK 11, banco PostgreSQL (utilizando Flyway para versionamento da base) e é hospedada no Heroku.

Endereço da aplicação na nuvem: http://tecnomarias.herokuapp.com/api/v1

## Swagger UI

A documentação da API foi feita com OpenAPI e disponibilizada pela UI do Swagger. 

Você pode acessar a documentação e testar a API em http://tecnomarias.herokuapp.com/api/v1/doc.

## Rodando a aplicação

O projeto possui um wrapper do Maven, portanto não é necessário ter a ferramenta instalada. 

É necessário uma conexão com banco PostgreSQL, com uma base nomeada `tecnomarias`. As configurações de conexão podem ser alteradas no arquivo `application.properties`.

>Devido a configurações necessárias para o Heroku, algumas classes de configuração são utilizadas que não funcionam em ambiente local. Para rodar corretamente, é necessário comentar o conteúdo da classe `DatabaseConfig`.

Para rodar a aplicação localmente, na raíz do projeto usar comando:

```shell
./mvnw spring-boot:run
```

<b>URL local:</b> localhost:8080/api/v1
