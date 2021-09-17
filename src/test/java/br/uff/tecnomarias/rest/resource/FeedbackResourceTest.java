package br.uff.tecnomarias.rest.resource;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeedbackResourceTest {

    @LocalServerPort
    private Integer port;
    private static final String BASE_PATH = "http://localhost:%s/api/v1/feedback/";

    private static final Long ID_USUARIA_SEM_FEEDBACK = 11L;
    private static final Long ID_USUARIA_COM_FEEDBACK = 8L;
    private static final Long ID_FEEDBACK_PARA_REMOVER = 17L;

    @Test
    @DisplayName("TM-55: Criar feedback falha por requisição inválida")
    void deveSalvarFeedbackComSucesso() {
        String json = "{\"comentario\":\"bom site\"}";
        given().contentType(ContentType.JSON)
                .body(json)
                .pathParam("idPessoa", ID_USUARIA_SEM_FEEDBACK)
                .when()
                .post(String.format(BASE_PATH, port)+"{idPessoa}")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", notNullValue(),
                        "comentario", is("bom site"));
    }

    @Test
    @DisplayName("TM-56: Remover feedback com sucesso")
    void deveRemoverFeedbackComSucesso() {
        given().pathParam("id", ID_FEEDBACK_PARA_REMOVER)
                .when()
                .delete(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TM-57: Remover feedback falha por id não encontrado")
    void deveRetornarNotFoundRemoverFeedbackNaoCadastrado() {
        given().pathParam("id", 999)
                .when()
                .delete(String.format(BASE_PATH, port)+"{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("TM-59: Criar feedback falha por usuária já ter feito feedback")
    void deveRetornarBadRequestSalvarMaisDeUmFeedback() {
        String json = "{\"comentario\":\"bom site\"}";
        given().contentType(ContentType.JSON)
                .body(json)
                .pathParam("idPessoa", ID_USUARIA_COM_FEEDBACK)
                .when()
                .post(String.format(BASE_PATH, port)+"{idPessoa}")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", is("Usuaria ja availou o site"));
    }

    @Test
    @DisplayName("TM-80: Buscar os três feedbacks mais recentes")
    void deveListar3Feedbacks() {
        when()
                .get(String.format(BASE_PATH, port))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", is(3));
    }
}
