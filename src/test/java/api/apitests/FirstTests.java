package api.apitests;

import org.testng.annotations.Test;
import api.utils.ApiUtils;

import java.util.Map;

public class FirstTests {

    //private static final String KB_DASHBOARD_PAGE_URL = "http://localhost/kanboard/?controller=DashboardController&action=show";
    private static final String BASE_URL = "http://127.0.0.1/kanboard/";
    private static final String BASE_URL_local = "http://localhost/dashboard/";
    @Test
    public final void testGetCookie() {
        Map<String, String> cookies = ApiUtils.getCookie(BASE_URL);
        System.out.println(cookies.values());

    }


}
