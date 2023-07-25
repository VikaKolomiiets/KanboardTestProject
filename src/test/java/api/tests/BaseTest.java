package api.tests;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.devtools.v85.domsnapshot.model.ArrayOfStrings;
import org.testng.annotations.*;
import org.testng.util.Strings;

import java.util.ArrayList;
import java.util.List;

import static api.utils.EnvProperties.BASE_URL;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


public class BaseTest {

//    @BeforeTest
//    public void setUpTests(){
//        Configuration.baseUrl = BASE_URL;
//    }
//
//    @BeforeClass
//    @Parameters({"Browser type"})
//    public void setUpClass(String browserType) {
//        if(browserType.contains("headless")){
//            Configuration.headless = true;
//        } else {
//            Configuration.browser = browserType;
//        }
//    }
//
//

    @BeforeClass
    public void setUpClass(){
        Configuration.baseUrl = BASE_URL;
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        closeWebDriver();
    }

//    @AfterTest
//    public void tearDownTests(){
//        System.out.println("All browsers were tested");
//    }
}
