package api.automacao.produtos;

import api.automacao.usuario.UsuarioTest;
import io.restassured.*;
import io.restassured.http.ContentType;
import org.hamcrest.*;
import org.junit.jupiter.api.*;
import provider.*;
import core.UrlBase;
import static org.hamcrest.Matchers.*;


public class ProdutosTest extends UrlBase {

    @BeforeAll
    public static void autenticacaoAntesDoTeste() {
        UsuarioTest usuarioTeste = new UsuarioTest();
        usuarioTeste.autenticaLoginComSucesso();
    }

    @Test
    @DisplayName("Cenário 1: Teste autenticando a rota de consultar os produtos com sucesso - Status Code = 201")
    public void autenticaProduto() {
        String token = GeradorLogin.getToken();
        RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("auth/products")
                .then()
                .statusCode(200)
                .body("products",Matchers.is(notNullValue()))
                .body("total", Matchers.is(100))
                .log().everything();
    }

    @Test
    @DisplayName("Cenário 2: Teste autenticando a rota de consultar os produtos com falha - Status Code = 403")
    public void autenticaProdutoFalha(){
        RestAssured.given()
                .when()
                .get("auth/products")
                .then()
                .statusCode(403)
                .body("message", Matchers.is("Authentication Problem"))
                .log().everything();
    }

    @Test
    @DisplayName("Cenário 3: Teste autenticando a rota de consultar os produtos com token expirado - Status Code = 401")
    public void autenticaProdutoTokenExpirado() {
        RestAssured.given()
                .header("Authorization", "Bearer " + 1234)
                .when()
                .get("auth/products")
                .then()
                .statusCode(401)
                .body("message", Matchers.is("Invalid/Expired Token!"))
                .log().everything();
    }

    @Test
    @DisplayName("Cenário 4: Teste adicionando um novo produto - Status Code = 201")
    //TODO: Bug encontrado - Expectativa de status code na documentaçao = 201
    public void adicionaNovoProduto() {
        Produtos produto = GeradorProdutos.obterProdutoPadrao();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(produto)
                .when()
                .post("products/add")
                .then().statusCode(200)
                .body("id", equalTo(101))
                .body("title", is("Perfume Oil"))
                .log().everything();

    }
    @Test
    @DisplayName("Cenário 5: Teste adicionando um novo produto diferente - Status Code = 201")
    //TODO: Bug encontrado - Expectativa de status code na documentaçao = 201
    public void adicionaNovoProdutoDiferente() {
        Produtos produto = GeradorProdutos.obterProdutoDiferente();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(produto)
                .when()
                .post("products/add")
                .then().statusCode(200)
                .body("title", is("Perfume Oil 2"))
                .body("category", Matchers.is("teste 123"))
                .log().everything();
    }

    @Test
    @DisplayName("Cenário 6: Teste consultando todos os produtos - Status Code = 200")
    public void consultaTodosProdutos() {
        RestAssured.given()
                .when()
                .get("products")
                .then().statusCode(200)
                .body("products",Matchers.is(notNullValue()))
                .log().everything();
    }

    @Test
    @DisplayName("Cenário 7: Teste consultando um produto com id com sucesso - Status Code = 200")
    public void consultaProdutoCadastrado() {
        RestAssured.given()
                .when()
                .get("products/1")
                .then().statusCode(200)
                .body("title", Matchers.is("iPhone 9"))
                .log().everything();
    }

    @Test
    @DisplayName("Cenário 8: Teste consultando mais produto com id com sucesso - Status Code = 200")
    public void consultaProdutoCCadastradoComSucesso() {
        RestAssured.given()
                .when()
                .get("products/5")
                .then().statusCode(200)
                .body("category", Matchers.is("smartphones"))
                .log().everything();
    }

    @Test
    @DisplayName("Cenário 9: Teste consultando um produto com id inexistente - Status Code = 404")
    public void consultaProdutoIdInexistente() {
        RestAssured.given()
                .when()
                .get("products/0")
                .then().statusCode(404)
                .body("message", Matchers.is("Product with id '0' not found"))
                .log().everything();
    }

    @Test
    @DisplayName("Cenário 10: Teste consultando um produto com id inexistente - Status Code = 404")
    public void consultaProdutoInexistente() {
        RestAssured.given()
                .when()
                .get("products/1001")
                .then().statusCode(404)
                .body("message", Matchers.is("Product with id '1001' not found"))
                .log().everything();
    }
}







