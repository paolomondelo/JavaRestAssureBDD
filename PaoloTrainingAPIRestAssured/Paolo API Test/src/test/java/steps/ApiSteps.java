package steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import pages.ApiPage;

public class ApiSteps {
    ApiPage apiPage = new ApiPage();
    Response response;

    @Given("I send a GET request to {string}")
    public void iSendAGETRequestTo(String url) {
        response = apiPage.getRequest(url);
    }

    @Given("I send a POST request to {string} with body")
    public void iSendAPOSTRequestToWithBody(String url, String body) {
        response = apiPage.postRequest(url, body);
    }

    @Given("I send a PUT request to {string} with body")
    public void iSendAPUTRequestToWithBody(String url, String body) {
        response = apiPage.putRequest(url, body);
    }

    @Given("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String url) {
        response = apiPage.deleteRequest(url);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

    @Then("the response should have {string} with value {int}")
    public void theResponseShouldHaveWithValue(String key, int value) {
        response.then().assertThat().body("$", hasKey(key));
        assertThat(response.jsonPath().getInt(key), equalTo(value));
    }

    @Then("the response should have {string} with value {string}")
    public void theResponseShouldHaveWithValue(String key, String value) {
        response.then().assertThat().body("$", hasKey(key));
        assertThat(response.jsonPath().getString(key), equalTo(value));
    }
    
    @Then("the response should have {string} with value")
    public void theResponseShouldHaveWithDocStringValue(String key, String docString) {
        response.then().assertThat().body("$", hasKey(key));
        String actualValue = response.jsonPath().getString(key);
        // Remove any trailing/leading whitespace and normalize line endings
        String expectedValue = docString.trim();
        String normalizedActual = actualValue.trim();
        assertThat(normalizedActual, equalTo(expectedValue));
    }
}