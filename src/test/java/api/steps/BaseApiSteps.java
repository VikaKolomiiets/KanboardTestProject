package api.steps;

import api.models.BodyArgs;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseApiSteps {
    private static final String API_URL = "http://127.0.0.1/jsonrpc.php";
    public static Response restAssurePost(String username, String password, BodyArgs bodyArgs){
        return RestAssured.given()
                .auth().basic(username, password)
                .body(bodyArgs)
                .when()
                .post(API_URL);
    }
}
