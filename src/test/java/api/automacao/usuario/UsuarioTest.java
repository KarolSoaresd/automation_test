package api.automacao.usuario;

import io.restassured.*;
import io.restassured.http.ContentType;
import org.hamcrest.*;
import org.junit.jupiter.api.*;
import provider.*;
import core.UrlBase;

import static helper.usuarioAPI.falhaGerarToken;
import static helper.usuarioAPI.gerarTokenUsuario;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsuarioTest extends UrlBase {

    public static final String ERRO_CREDENCIAL = "Invalid credentials";

    @Test
    @DisplayName("Cenário 1: Teste validando login com sucesso - Status Code = 201")
    //TODO: Bug encontrado - Expectativa de status code na documentaçao = 201
    public void autenticaLoginComSucesso() {
        // gerar payload
        Login login = GeradorLogin.obterLoginPadrao();
        String token = gerarTokenUsuario(login);

        // gerar token com sucesso
        GeradorLogin.setToken(token);
    }

    @Test
    @DisplayName("Cenário 2: Teste validando login com falha informando usuario incorreto - Status Code = 400")
        public void autenticaLoginComFalhaUsuario() {
        // gerar payload
        var payload = Login.builder().username("kminchellee").password("0lelplR").build();
        String mensagemErro = falhaGerarToken(payload);

        // Assert
        assertThat(mensagemErro, equalTo(ERRO_CREDENCIAL));
    }

    @Test
    @DisplayName("Cenário 3: Teste validando login com falha informando senha incorreta - Status Code = 400")
    public void autenticaLoginComFalhaSenha() {
        var payload = Login.builder().username("kminchelle").password("11111").build();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("auth/login")
                .then().statusCode(400)
                .body("message", Matchers.is("Invalid credentials"))
                .log().everything();
    }

    @Test
    @DisplayName("Cenário 4: Teste validando a busca de usuários com sucesso - Status Code = 200")
    public void buscaUsuario() {
        RestAssured.given()
                .when()
                .get("/users")
                .then().statusCode(200)
                .body("users", Matchers.is(notNullValue()))
                .log().everything();
    }

    @Test
    @DisplayName("Cenário 5: Teste validando a busca de usuários com sucesso validando username - Status Code = 200")
    public void buscaUsuarioPorUsername() {
        RestAssured.given()
                .when()
                .get("/users")
                .then().statusCode(200)
                .body("users", Matchers.is(notNullValue()))
                .body("users.find { it.id == 1 }.username", equalTo("atuny0"))
                .log().everything();
    }

    @Test
    @DisplayName("Cenário 6: Teste validando a busca de usuários com sucesso validando password - Status Code = 200")
    public void buscaUsuarioPorPassword() {
        RestAssured.given()
                .when()
                .get("/users")
                .then().statusCode(200)
                .body("users", Matchers.is(notNullValue()))
                .body("users.find { it.id == 1 }.password", equalTo("9uQFF1Lh"))
                .log().everything();
    }
}
