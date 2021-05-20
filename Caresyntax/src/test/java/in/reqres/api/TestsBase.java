package in.reqres.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.http.ContentType.JSON;

public class TestsBase {

    private final String host = System.getProperty("host.dev");
    private final String basePath = "/api";

    protected RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType(JSON)
                .setAccept(JSON)
                .addFilter(new AllureRestAssured())
                .setBaseUri(host)
                .setBasePath(basePath)
                .build();

    @BeforeAll
    static void enableLogging(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
