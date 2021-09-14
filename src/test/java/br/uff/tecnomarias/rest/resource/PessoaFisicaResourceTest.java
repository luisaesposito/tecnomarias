package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.domain.entity.Links;
import br.uff.tecnomarias.rest.dto.PessoaFisicaDTO;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaFisicaResourceTest {

    @LocalServerPort
    private Integer port;
    private static final String BASE_PATH = "http://localhost:%s/api/v1/usuaria/";

    private static final Long ID_USUARIA_SALVA = 8L;
    private static final Long ID_USUARIA_PARA_ALTERAR = 9L;
    private static final Long ID_USUARIA_PARA_REMOVER = 11L;

    @Test
    @DisplayName("TM-10: Criar perfil pessoa física com sucesso")
    void deveCriarUsuariaComSucesso() {
        String json = "{\"tipoPessoa\":\"PF\",\"nome\":\"Ana Fulana\",\"email\":\"ana@gmail.com\"," +
                "\"links\":{\"gitHub\":\"ana\",\"portfolio\":\"portfolio.com/ana\"}}";
        given().contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(String.format(BASE_PATH, port))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("TM-11: Criar perfil pessoa física falha por requisição inválida")
    void deveRetornarErroBadRequestSalvarComBodyInvalido() {
        String json = "{\"tipoPessoa\":\"PF\",\"nome\":\"       \",\"email\":\"ana@gmail.com\"," +
                "\"links\":{\"gitHub\":\"ana\",\"portfolio\":\"portfolio.com/ana\"}}";
        given().contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(String.format(BASE_PATH, port))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

        json = "{\"tipoPessoa\":\"PF\",\"email\":\"ana@gmail.com\"," +
                "\"links\":{\"gitHub\":\"ana\",\"portfolio\":\"portfolio.com/ana\"}}";
        given().contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(String.format(BASE_PATH, port))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("TM-12: Editar perfil pessoa física com sucesso")
    void deveAtualizarUsuariaComSucesso() {
        PessoaFisicaDTO dto = given().pathParam("id", ID_USUARIA_PARA_ALTERAR)
                .get(String.format(BASE_PATH, port)+"{id}").then().extract().as(PessoaFisicaDTO.class);

        Links links = new Links();
        links.setGitHub("git_hub.com/ramos");
        dto.links = links;
        dto.nome = "Novo nome";

        given().contentType(ContentType.JSON)
                .body(dto)
                .pathParam("id", ID_USUARIA_PARA_ALTERAR)
                .when()
                .put(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nome", is("Novo nome"),
                        "links.gitHub", is("git_hub.com/ramos"));
    }

    @Test
    @DisplayName("TM-13: Editar perfil pessoa física falha por id não encontrado")
    void deveRetornarNotFoundAtualizarUsuariaNaoCadastrada() {
        PessoaFisicaDTO dto = given().pathParam("id", ID_USUARIA_SALVA)
                .get(String.format(BASE_PATH, port)+"{id}").then().extract().as(PessoaFisicaDTO.class);

        given().contentType(ContentType.JSON)
                .body(dto)
                .pathParam("id", 999)
                .when()
                .put(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("TM-49: Editar perfil pessoa física falha por requisição inválida")
    void deveRetornarBadRequestAtualizarComCampoInvalido() {
        PessoaFisicaDTO dto = given().pathParam("id", ID_USUARIA_SALVA)
                .get(String.format(BASE_PATH, port)+"{id}").then().extract().as(PessoaFisicaDTO.class);

        dto.nome = null;

        given().contentType(ContentType.JSON)
                .body(dto)
                .pathParam("id", ID_USUARIA_SALVA)
                .when()
                .put(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("TM-14: Remover perfil pessoa física com sucesso")
    void deveRemoverUsuariaComSucesso() {
        given()
                .pathParam("id", ID_USUARIA_PARA_REMOVER)
                .when()
                .delete(String.format(BASE_PATH, port)+"{id}")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TM-15: Remover perfil pessoa física falha por id não encontrado")
    void deveRetornarNotFoundRemoverUsuariaNaoCadastrada() {
        given()
                .pathParam("id", 999)
                .when()
                .delete(String.format(BASE_PATH, port)+"{id}")
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("TM-16: Visualizar perfil pessoa física com sucesso")
    void deveBuscarUsuariaPorIDComSucesso() {
        given()
                .pathParam("id", ID_USUARIA_SALVA)
                .when()
                .get(String.format(BASE_PATH, port)+"{id}")
                .then().statusCode(HttpStatus.SC_OK)
                .body("id", is(ID_USUARIA_SALVA.intValue()),
                        "nome", is("Graziela de Jesus"),
                        "email", is("grazielajj@gmail.com"));
    }

    @Test
    @DisplayName("TM-17: Visualizar perfil pessoa física falha por id não encontrado")
    void DeveRetornarNotFoundBuscarUsuariaNaoCadastrada() {
        given()
                .pathParam("id", 999)
                .when()
                .get(String.format(BASE_PATH, port)+"{id}")
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
