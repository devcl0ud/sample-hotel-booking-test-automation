package steps;

import config.Env;
import context.BookingContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utils.RequestBuilder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiStepDefinitions {

    private Response response;
    private String adminToken;

    @Given("the API base URL is set")
    public void the_api_base_url_is_set() {}

    @When("request to create new booking at {string} with content:")
    public void create_booking(String endpoint, String jsonBody) {
        response = RequestBuilder.json()
                .body(jsonBody)
                .post(endpoint)
                .then()
                .extract()
                .response();
    }
    @Then("the response status code should be {int}")
    public void verify_status_code(Integer statusCode) {
        System.out.println(response.getBody().prettyPrint());
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("the response should contain valid {string}")
    public void verify_booking(String key) {
        int bookingId = response.jsonPath().getInt(key);
        assertTrue("Booking id should be a positive integer", bookingId > 0);
        BookingContext.getInstance().setBookingId(bookingId);
    }

    @Then("the response should contain {string} and {string} with values {string} and {string}")
    public void verify_booking_content(String firstnameKey, String lastnameKey, String firstnameValue, String lastnameValue) {
        assertEquals(firstnameValue, response.jsonPath().getString(firstnameKey));
        assertEquals(lastnameValue, response.jsonPath().getString(lastnameKey));
    }

    @When("request to delete last booking at {string}")
    public void delete_booking(String endpoint) {
        response = RequestBuilder.json()
                .header("Cookie", "token=" + this.adminToken)
                .delete(endpoint + "/"  + BookingContext.getInstance().getBookingId())
                .then()
                .extract()
                .response();
    }

    @Given("Generate the valid token")
    public void generate_token(){
        String endpoint = "/auth/login";
        int validStatusCode = 200;
        String tokenKey= "token";

        response = RequestBuilder.json()
                .body(Env.getCredentials())
                .post(endpoint)
                .then()
                .extract()
                .response();
        this.verify_status_code(validStatusCode);
        this.adminToken = response.jsonPath().getString(tokenKey);
    }
}