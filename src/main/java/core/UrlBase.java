package core;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class UrlBase {

    @BeforeAll
    public static void preCondition() {
        baseURI = "https://dummyjson.com/";
        enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
