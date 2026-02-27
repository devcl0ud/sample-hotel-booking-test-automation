package utils;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestBuilder {

    // Basic JSON request
    public static RequestSpecification json() {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .log().all();
    }
}