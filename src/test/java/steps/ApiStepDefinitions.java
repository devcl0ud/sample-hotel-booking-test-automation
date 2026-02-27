package steps;

import config.Env;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ApiStepDefinitions {

    private Response response;

    @Given("the API base URL is set")
    public void the_api_base_url_is_set() {}

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = given().when().header("x-api-key", Env.getApiKey()).get(endpoint).then().extract().response();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("the response should contain {string} with value {int}")
    public void the_response_should_contain_with_value(String key, Integer value) {
        assertEquals(value.intValue(), response.jsonPath().getInt(key));
    }

    @Then("the response should have {string} as {string}")
    public void the_response_should_contain_with_email(String key, String value) {
        assertEquals(value, response.jsonPath().getString(key));
    }
}