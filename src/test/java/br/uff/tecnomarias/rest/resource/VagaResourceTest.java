package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.rest.dto.VagaDTO;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VagaResourceTest {

    @LocalServerPort
    private Integer port;
    private static final String BASE_PATH = "http://localhost:%s/api/v1/vaga/";

    private static final Long ID_VAGA_SALVA = 5L;
    private static final Long ID_VAGA_PARA_ALTERAR = 6L;
    private static final Long ID_VAGA_PARA_REMOVER = 7L;
    private static final String AREA_ATUACAO_SALVA = "analise";

    @Test
    @DisplayName("TM-39: Criar vaga com sucesso")
    void deveCriarVagaComSucesso() {
        String json = "{\"idEmpresa\":\"1\",\"areaAtuacao\":\"arquitetura\",\"cargo\":\"JUNIOR\",\"descricao\":\"descricao da vaga\",\"valor\":3500,\"localidade\":\"Rio de Janeiro\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(String.format(BASE_PATH, port))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", notNullValue(),
                        "areaAtuacao", is("arquitetura"));
    }

    @Test
    @DisplayName("TM-40: Criar vaga falha por requisição inválida")
    void deveRetornarBadRequestSalvarBodyCampoInvalido() {
        String json = "{\"idEmpresa\":\"1\",\"areaAtuacao\":\"      \",\"cargo\":\"JUNIOR\",\"descricao\":\"descricao da vaga\",\"valor\":3500,\"localidade\":\"Rio de Janeiro\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(String.format(BASE_PATH, port))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

        json = "{\"idEmpresa\":\"1\",\"areaAtuacao\":\"      \",\"cargo\":\"JUNIOR\",\"descricao\":\"descricao da vaga\",\"valor\":3500,\"localidade\":\"Rio de Janeiro\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(String.format(BASE_PATH, port))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("")
    void deveRetornarBadRequestSalvarVagaParaEmpresaNaoCadastrada() {
        String json = "{\"idEmpresa\":\"999\",\"areaAtuacao\":\"arquitetura\",\"cargo\":\"JUNIOR\",\"descricao\":\"descricao da vaga\",\"valor\":3500,\"localidade\":\"Rio de Janeiro\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(String.format(BASE_PATH, port))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("TM-41: Editar vaga com sucesso")
    void deveAtualizarVagaComSucesso() {
        VagaDTO dto = given().pathParam("id", ID_VAGA_PARA_ALTERAR).when()
                .get(String.format(BASE_PATH, port)+"{id}").then().extract().as(VagaDTO.class);

        dto.valor = dto.valor + 500.2;

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .pathParam("id", ID_VAGA_PARA_ALTERAR)
                .when()
                .put(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("valor", is(2500.2f));
    }

    @Test
    @DisplayName("TM-42: Editar vaga falha por id não encontrado")
    void deveRetornarNotFoundAtualizarVagaNaoCadastrada() {
        VagaDTO dto = given().pathParam("id", ID_VAGA_PARA_ALTERAR).when()
                .get(String.format(BASE_PATH, port)+"{id}").then().extract().as(VagaDTO.class);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .pathParam("id", 999)
                .when()
                .put(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("TM-43: Editar vaga falha por requisição inválida")
    void deveRetornarBadRequestAtulizarVagaBodyCampoInvalido() {
        VagaDTO dto = given().pathParam("id", ID_VAGA_PARA_ALTERAR).when()
                .get(String.format(BASE_PATH, port)+"{id}").then().extract().as(VagaDTO.class);

        dto.areaAtuacao = null;

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .pathParam("id", ID_VAGA_PARA_ALTERAR)
                .when()
                .put(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("TM-44: Remover vaga com sucesso")
    void deveRemoverVagaComSucesso() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", ID_VAGA_PARA_REMOVER)
                .when()
                .delete(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TM-45: Remover vaga falha por id não encontrado")
    void deveRetornarNotFoundRemoverVagaNaoCadastrada() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 999)
                .when()
                .delete(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("TM-46: Visualizar vaga com sucesso")
    void deveBuscarVagaComSucesso() {
        given()
                .pathParam("id", ID_VAGA_SALVA)
                .when()
                .get(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(ID_VAGA_SALVA.intValue()),
                        "idEmpresa", is(1),
                        "areaAtuacao", is("analise de dados"),
                        "cargo", is("JUNIOR"));
    }

    @Test
    @DisplayName("TM-47: Visualizar vaga falha por id não encontrado")
    void deveRetornarNotFoundBuscarVagaNaoCadastrada() {
        given()
                .pathParam("id", 999)
                .when()
                .get(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("TM-65: Buscar todas vagas retorna todas vagas com sucesso")
    void deveRetornarTodasVagasCadastradas() {
        given()
                .when()
                .get(String.format(BASE_PATH, port))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThanOrEqualTo(3));
    }

    @Test
    @DisplayName("TM-67: Buscar vagas de uma empresa com sucesso")
    void deveRetornarTodasVagasDeEmpresa() {
        given()
                .pathParam("id", 1)
                .when()
                .get(String.format(BASE_PATH, port)+"empresa/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", is(0));
    }

    @Test
    @DisplayName("TM-68: Buscar vagas de uma empresa falha por id não encontrado")
    void deveRetornarListaVaziaEmpresaNaoEncontrada() {
        given()
                .pathParam("id", 999)
                .when()
                .get(String.format(BASE_PATH, port)+"empresa/{id}")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", is("Empresa nao encontrada"));
    }

    @Test
    @DisplayName("TM-69: Buscar vagas pelo atributo areaAtuacao com sucesso")
    void deveRetornarVagasComMesmaAreaAtuacao() {
        given()
                .queryParam("filtro", "areaAtuacao")
                .queryParam("valor", AREA_ATUACAO_SALVA)
                .when()
                .get(String.format(BASE_PATH, port)+"filtro")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThanOrEqualTo(2));
    }

    @Test
    @DisplayName("TM-70: Buscar vagas pelo atributo areaAtuacao retorna vazio")
    void deveRetornarListaVaziaAreaAtuacaoNaoEncontrada() {
        given()
                .queryParam("filtro", "areaAtuacao")
                .queryParam("valor", "nope")
                .when()
                .get(String.format(BASE_PATH, port)+"filtro")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", is(0));
    }

    @Test
    @DisplayName("TM-71: Buscar vagas pelo atributo cargo com sucesso")
    void deveRetornarVagasDeMesmoCargo() {
        given()
                .queryParam("filtro", "cargo")
                .queryParam("valor", "PLENO")
                .when()
                .get(String.format(BASE_PATH, port)+"filtro")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", is(1));
    }

    @Test
    @DisplayName("TM-72: Buscar vagas pelo atributo cargo retorna erro")
    void deveRetornarBadRequestCargoNaoEncontrado() {
        given()
                .queryParam("filtro", "cargo")
                .queryParam("valor", "nope")
                .when()
                .get(String.format(BASE_PATH, port)+"filtro")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", is("Cargo invalido"));
    }

    @Test
    @DisplayName("TM-73: Buscar vagas pelo atributo localidade com sucesso")
    void deveRetonarVagasDeMesmaLocalidade() {
        given()
                .queryParam("filtro", "localidade")
                .queryParam("valor", "Rio de Janeiro")
                .when()
                .get(String.format(BASE_PATH, port)+"filtro")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThanOrEqualTo(2));
    }

    @Test
    @DisplayName("TM-74: Buscar vagas pelo atributo localidade retorna vazio")
    void deveRetornarListaVaziaLocalidadeNaoEncontrada() {
        given()
                .queryParam("filtro", "localidade")
                .queryParam("valor", "nope")
                .when()
                .get(String.format(BASE_PATH, port)+"filtro")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", is(0));
    }

    @Test
    @DisplayName("TM-78: Buscar áreas de atuação das vagas")
    void deveRetornarTodasAreasAtuacao() {
        when()
                .get(String.format(BASE_PATH, port)+"area_atuacao")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", is(2));
    }

    @Test
    @DisplayName("TM-81: Buscar vagas por um atributo falha por filtro invalido")
    void deveRetornarBadRequestPassarFiltroInvalido() {
        given()
                .queryParam("filtro", "nope")
                .queryParam("valor", "nope")
                .when()
                .get(String.format(BASE_PATH, port)+"filtro")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", is("Filtro invalido"));
    }

}
