package br.uff.tecnomarias.rest.resource;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaJuridicaResoureceTest {

    @LocalServerPort
    private Integer port;

    @Test
    void deveSalvarEmpresaComSucesso() {
        String json = "{\"nome\":\"nova empresa\",\"tipoPessoa\":\"PJ\",\"email\":\"teste@email.com\",\"cnpj\":\"84072258000193\",\"porteEmpresa\":\"MICROEMPRESA\",\"areaAtuacao\":\"area\"}";
        given().contentType("application/json")
                .body(json)
                .when().post("http://localhost:"+port+"/api/v1/empresa")
                .then().statusCode(200).log().all()
                .body("id", notNullValue());
    }

}
