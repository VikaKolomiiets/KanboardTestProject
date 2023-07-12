package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {


    private static final String BASE_URL = "http://127.0.0.1/kanboard/";

    public static Map<String, String> getCookie() {
        RestAssured.requestSpecification = new RequestSpecBuilder().setBaseUri("api").setContentType(ContentType.JSON)
                .build().log().all();

        Response response01 = given()
                .queryParam("controller", "AuthController")
                .queryParam("action", "login")
                .when()
                .get(BASE_URL);

        String cSRFToken = Jsoup.parseBodyFragment(response01.body().asString())
                .getElementsByAttributeValue("name", "csrf_token").attr("value");

        String cookieKBSID = response01.getCookie("KB_SID");


        Response response02 = given()
                .config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("remember_me", "1")
                .formParam("username", "admin")
                .formParam("password", "admin")
                .formParam("csrf_token", cSRFToken)
                .queryParam("controller", "AuthController")
                .queryParam("action", "check")
                .cookie("KB_SID", cookieKBSID)
                .when()
                .post(BASE_URL);

        String setCookieHeaderValue = response02.header("Set-Cookie");
        Map<String, String> cookieKBRMMap = new HashMap<String, String>();
        for (String keyValue : setCookieHeaderValue.split(" *; *")) {
            String[] pairs = keyValue.split(" *= *", 2);
            cookieKBRMMap.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
        }

        Map<String, String> cookies = new HashMap<>();
        cookies.put("KB_SID", cookieKBSID);
        cookies.put("KB_RM", cookieKBRMMap.get("KB_RM"));
        return cookies;
    }
}
