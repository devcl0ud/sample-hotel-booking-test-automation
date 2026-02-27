package hooks;

import io.cucumber.java.Before;
import config.ApiConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class TestHooks {
    static final java.util.Map<String, String> STATUS = java.util.Collections.singletonMap("status", "UP");
    @Before
    public void beforeScenario() {
        ApiConfig.setup();
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("/booking/actuator/health")
                .then()
                .extract()
                .response();

        assertEquals("Health check failed!", 200, response.getStatusCode());
        assertEquals("status needs to be up", STATUS.get("status"), response.jsonPath().getString("status"));

    }
}