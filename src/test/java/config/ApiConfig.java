package config;

import io.restassured.RestAssured;

public class ApiConfig {
    public static void setup() {
        RestAssured.baseURI = "https://automationintesting.online/api";

    }
}