package api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import api.utilsold.ApiUtils;

import java.util.Map;

public class FirstTests {

    //private static final String KB_DASHBOARD_PAGE_URL = "http://localhost/kanboard/?controller=DashboardController&action=show";
    private static final String BASE_URL = "http://127.0.0.1/kanboard/";
    private static final String BASE_URL_local = "http://127.0.0.1/dashboard/";

    @Test
    public void testGetCookie() {
        Map<String, String> cookies = ApiUtils.getCookie(BASE_URL);
        System.out.println(cookies.values());

    }

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
