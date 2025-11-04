package pages;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class ApiPage {
    public Response getRequest(String url) {
        return RestAssured.get(url);
    }

    public Response postRequest(String url, String body) {
        return given().header("Content-Type", "application/json").body(body).post(url);
    }

    public Response putRequest(String url, String body) {
        return given().header("Content-Type", "application/json").body(body).put(url);
    }

    public Response deleteRequest(String url) {
        return RestAssured.delete(url);
    }
}