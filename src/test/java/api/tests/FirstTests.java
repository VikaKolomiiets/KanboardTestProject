package api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import api.utils.ApiUtils;

import java.util.Map;

public class FirstTests {

    private static final String BASE_URL_UI = "http://127.0.0.1/kanboard/";
    private static final String BASE_URL_local = "http://127.0.0.1/dashboard/";
    private static final String BASE_URL = "http://127.0.0.1/";


    @Test
    public void testGet(){
        Response response = RestAssured.get(BASE_URL_local);
        System.out.println(response.getHeaders().asList().get(0));
        response.getBody().prettyPrint();
    }

    @Test
    public void testGetAnother(){
        Response response = RestAssured.get(BASE_URL);
        System.out.println(response.getHeaders().asList().get(0));
        response.getBody().prettyPrint();
    }


}
