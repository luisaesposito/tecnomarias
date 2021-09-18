package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.rest.dto.PessoaJuridicaDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PessoaJuridicaResourceTest {

    @LocalServerPort
    private Integer port;
    private static final String BASE_PATH = "http://localhost:%s/api/v1/empresa/";

    private static final Long ID_EMPRESA_SALVA = 1L;
    private static final Long ID_EMPRESA_PARA_REMOVER = 2L;

    @Test
    @DisplayName("TM-1 : Criar perfil empresa com sucesso")
    void deveSalvarEmpresaComSucesso() {
        String json = "{\"nome\":\"nova empresa\",\"tipoPessoa\":\"PJ\",\"email\":\"teste@email.com\",\"cnpj\":\"84072258000193\",\"porteEmpresa\":\"MICROEMPRESA\",\"areaAtuacao\":\"area\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(String.format(BASE_PATH, port))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("TM-2 : Criar perfil empresa falha por requisição inválida")
    void deveRetornarErroBadRequestSalvarComBodyInvalido() {
        String json = "{\"nome\":\"nova empresa\",\"tipoPessoa\":\"PJ\",\"email\":\"teste@email.com\",\"cnpj\":\"   \",\"porteEmpresa\":\"MICROEMPRESA\",\"areaAtuacao\":\"area\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(String.format(BASE_PATH, port))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("TM-3 : Editar perfil empresa com sucesso")
    void deveAtualizarEmpresaComSucesso() {
        PessoaJuridicaDTO dto = given().pathParam("id", ID_EMPRESA_SALVA)
                .get(String.format(BASE_PATH, port) + "{id}")
                .then().statusCode(HttpStatus.SC_OK).extract().as(PessoaJuridicaDTO.class);

        dto.setDescricao("nova descricao");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .pathParam("id", Long.valueOf(ID_EMPRESA_SALVA))
                .when()
                .put(String.format(BASE_PATH, port) + "{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(ID_EMPRESA_SALVA.intValue()),
                        "descricao", is("nova descricao"));
    }

    @Test
    @DisplayName("TM-4 : Editar perfil empresa falha por id não encontrado")
    void deveRetornarNotFoundAtualizarEmpresaNaoCadastrada() {
        Response response = given().pathParam("id", ID_EMPRESA_SALVA)
                .get(String.format(BASE_PATH, port) + "{id}")
                .then().statusCode(200).extract().response();
        String json = response.getBody().asString();
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .pathParam("id", "999")
                .when()
                .put(String.format(BASE_PATH, port) + "{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("TM-5 : Remover perfil empresa com sucesso")
    void deveRemoverEmpresa() {
        given()
                .pathParam("id", ID_EMPRESA_PARA_REMOVER)
                .when()
                .delete(String.format(BASE_PATH, port) + "{id}")
                .then().statusCode(HttpStatus.SC_OK)
                .body(is("Empresa removida com sucesso."));
    }

    @Test
    @DisplayName("TM-6 : Remover perfil empresa falha por id não encontrado")
    void deveRetornarNotFoundRemoverEmpresaNaoCadastrada() {
        given()
                .pathParam("id", 999)
                .when()
                .delete(String.format(BASE_PATH, port) + "{id}")
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("TM-7 : Visualizar perfil empresa com sucesso ")
    void deveBuscarPorIDComSucesso() {
        given()
                .pathParam("id", ID_EMPRESA_SALVA)
                .when()
                .get(String.format(BASE_PATH, port) + "{id}")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TM-8 : Visualizar perfil empresa falha por id não encontrado")
    void deveRetornarNotFoundBuscarPorIDNaoCadastrado() {
        given()
                .pathParam("id", 999)
                .get(String.format(BASE_PATH, port) + "{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("TM-9 : Editar perfil empresa falha por requisição inválida")
    void deveRetornarBadRequestAtualizarComBodyInvalido() {
        PessoaJuridicaDTO dto = given().pathParam("id", ID_EMPRESA_SALVA)
                .get(String.format(BASE_PATH, port) + "{id}")
                .then().statusCode(HttpStatus.SC_OK).extract().as(PessoaJuridicaDTO.class);

        dto.setCnpj("   ");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .pathParam("id", ID_EMPRESA_SALVA)
                .when()
                .put(String.format(BASE_PATH, port) + "{id}")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("TM-50 : Criar avaliação de empresa com sucesso")
    void deveSalvarAvaliacaoComSucesso() {
        String json = "{\"comentario\": \"Adorei a empresa\", \"nota\": 5,  \"nomeAvaliadora\": \"Samantha Costa\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .pathParam("id", Long.valueOf(ID_EMPRESA_SALVA))
                .when()
                .post(String.format(BASE_PATH, port) + "{id}/avaliacao")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", notNullValue(),
                        "idEmpresa", equalTo(ID_EMPRESA_SALVA.intValue()));
    }

    @Test
    @DisplayName("TM-51 : Criar avaliação de empresa falha por requisição inválida")
    void deveRetornarBadRequestSalvarAvaliacaoComBodyInvalido() {
        String json = "{\"comentario\": \"Adorei a empresa\", \"nota\": ,  \"nomeAvaliadora\": \"Samantha Costa\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .pathParam("id", ID_EMPRESA_SALVA)
                .when()
                .post(String.format(BASE_PATH, port) + "{id}/avaliacao")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("TM-52 : Remover avaliação de empresa com sucesso")
    void deveRemoverAvaliacaoComSucesso() {
        given()
                .pathParam("id", 19)
                .when()
                .delete(String.format(BASE_PATH, port) + "avaliacao/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(is("Avaliação removida com sucesso."));
    }

    @Test
    @DisplayName("TM-53 : Remover avaliação de empresa falha por id não encontrado")
    void deveRetornarBadRequestRemoverAvaliacaoComBodyInvalido() {
        given()
                .pathParam("id", 999)
                .when()
                .delete(String.format(BASE_PATH, port) + "avaliacao/{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("TM-58 : Criar avaliação de empresa falha por id não encontrado")
    void deveRetornarNotFoundSalvarAvaliacaoComEmpresaNaoCadastrada() {
        String json = "{\"comentario\": \"Adorei a empresa\", \"nota\": 5,  \"nomeAvaliadora\": \"Samantha Costa\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .pathParam("id", 999)
                .when()
                .post(String.format(BASE_PATH, port) + "{id}/avaliacao")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("TM-61: Buscar todos perfis empresa retorna todas as empresas com sucesso")
    void deveRetornarListaDeEmpresas() {
        when()
                .get(String.format(BASE_PATH, port))
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(2),
                        "id", hasItems(ID_EMPRESA_SALVA.intValue(), 30));
    }
}
