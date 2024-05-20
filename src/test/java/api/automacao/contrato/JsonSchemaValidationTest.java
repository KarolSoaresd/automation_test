package api.automacao.contrato;

import api.automacao.usuario.UsuarioTest;
import core.UrlBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import io.restassured.module.jsv.JsonSchemaValidator;
import provider.GeradorLogin;
import provider.Login;

public class JsonSchemaValidationTest extends UrlBase {

    @BeforeAll
    public static void autenticacaoAntesDoTeste() {
        UsuarioTest usuarioTeste = new UsuarioTest();
        usuarioTeste.autenticaLoginComSucesso();
    }

    @Test
    @DisplayName("Teste validando o Json Schema da saúde da aplicação - Get Request")
    public void testStatusDaAplicacao() {
        InputStream statusDaAplicacao = getClass().getClassLoader()
                .getResourceAsStream("getStatusDaAplicacao.json");

        RestAssured.given()
                .when()
                .get("/test")
                .then()
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(statusDaAplicacao))
                .log().everything();
    }

    @Test
    @DisplayName("Teste validando o Json Schema da busca de usuários - Get Request")
    public void testGetUsuarios() {
        InputStream getUsuarios = getClass().getClassLoader()
                .getResourceAsStream("getUsuarios.json");

        RestAssured.given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(getUsuarios))
                .log().everything();
    }

    @Test
    @DisplayName("Teste validando o Json Schema da autenticacao de login - POST Request")
    //TODO: Bug encontrado - Expectativa de status code na documentaçao = 201
    public void testAutenticacaoLogin() {
        Login login = GeradorLogin.obterLoginPadrao();
        InputStream getAutenticacaoLogin = getClass().getClassLoader()
                .getResourceAsStream("postAutenticacaoLogin.json");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(getAutenticacaoLogin))
                .log().everything();
    }

    @Test
    @DisplayName("Teste validando o Json Schema da autenticacao de produtos - POST Request")
    public void testAutenticacaoProduto() {
        String token = GeradorLogin.getToken();
        InputStream getAutenticacaoProduto = getClass().getClassLoader()
                .getResourceAsStream("postAutenticacaoProduto.json");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/auth/products")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(getAutenticacaoProduto))
                 .log().everything();
    }

    @Test
    @DisplayName("Teste validando o Json Schema de produtos - GET Request")
    public void testBuscaProdutos() {
        InputStream getBuscaProduto = getClass().getClassLoader()
                .getResourceAsStream("getProdutos.json");

        RestAssured.given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(getBuscaProduto))
                .log().everything();
    }
}
