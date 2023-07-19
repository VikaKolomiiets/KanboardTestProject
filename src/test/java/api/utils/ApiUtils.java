package api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    //private static final String API_URL = "http://127.0.0.1/jsonrpc.php";
    private static final String BASE_URL = "http://127.0.0.1/";


    private static String getTestPost() {

        Response postRequest = RestAssured.given().
                auth().
                basic("admin", "admin").
                header("Content-Type", "application/json").
//                header("Cookie", "KB_SID=sufseo930noe2qbr1smedqumpe").
//
                body("{\"jsonrpc\": \"2.0\", \"method\": \"getMyProjects\", \"id\": 1}").  //from Documentation
                post("http://localhost:80/jsonrpc.php");
        postRequest.prettyPrint();

        String s = postRequest.asString();
        return s;
    }


    private static String getKeyJsonPair(String pair){
        return Arrays.stream(pair.split(":")).collect(Collectors.toList()).get(0);
    }
    public static void main(String[] args) {
        System.out.println(getTestPost());
        //System.out.println(getKeyJsonPair("\"hft\":kwji"));
    }

}
