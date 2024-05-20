package helper;

import core.UrlBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import provider.Login;

import static io.restassured.RestAssured.given;

@Log
public class usuarioAPI extends UrlBase {

    public static String gerarTokenUsuario(Login jsonBody) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                    .post("auth/login")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("firstName", Matchers.is("Jeanne"))
                    .log()
                        .ifValidationFails()
                            .extract()
                                .response();

        return response.jsonPath().get("token");
    }

    public static String falhaGerarToken(Login jsonBody) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                    .post("auth/login")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .log()
                        .ifValidationFails()
                            .extract()
                                .response();

        return response.jsonPath().get("message");
    }
}
