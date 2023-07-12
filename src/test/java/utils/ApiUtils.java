package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jsoup.Jsoup;

import java.io.UnsupportedEncodingException;
import java.net.http.HttpHeaders;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    //private static final String BASE_URL = "http://127.0.0.1/kanboard/";
    private static final String BASE_URL = "http://127.0.0.1/";

    public static Map<String, String> getCookie(String url) {
        RestAssured.requestSpecification = new RequestSpecBuilder().setBaseUri("api").setContentType(ContentType.JSON)
                .build().log().all();

        Response response01 = given()
                .queryParam("controller", "AuthController")
                .queryParam("action", "login")
                .when()
                .get(url);

        String string01 = response01.body().asString();
        String cSRFToken = Jsoup.parseBodyFragment(string01)
                .getElementsByAttributeValue("name", "csrf_token").attr("value");

        String cookieKBSID = response01.getCookie("KB_SID");


        Response response02 = given()
                .config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("remember_me", "1")
                .formParam("username", "admin")
                .formParam("password", "admin")
                .formParam("csrf_token", cSRFToken)
                .queryParam("controller", "AuthController")
                .queryParam("action", "check")
                .cookie("KB_SID", cookieKBSID)
                .when()
                .post(url);

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
    private static String getToken() {

        Response postRequest = RestAssured.given().
                auth().
                basic("admin", "admin").
                header("Content-Type", "application/json").
                //header("Cookie", "KB_SID=sufseo930noe2qbr1smedqumpe").
                and().
                body("{\"jsonrpc\": \"2.0\", \"method\": \"getMyProjects\", \"id\": 1}").
                post("http://localhost:80/jsonrpc.php");
        // postRequest.prettyPrint();
        String s = postRequest.asString();
        return s;
    }

    public static void main(String[] args) {
        //Map<String, String> temp = ApiUtils.getCookie(BASE_URL);
        String temp =getToken();
        System.out.println(temp);

    }
}
