package api.automacao;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import core.UrlBase;

public class HealthTest extends UrlBase {
    @Test
    @DisplayName("Cenário 1: Teste validando a saúde da aplicaçao com sucesso - Status Code = 200")
    public void TesteAplicacao() {
        RestAssured.given()
                .when()
                .get("/test")
                .then().statusCode(200)
                .body("status", Matchers.is("ok"))
                .body ("method", Matchers.is("GET"))
                .log().everything();
    }
}