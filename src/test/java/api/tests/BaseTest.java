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



//    @DataProvider(name = "data-provider")
//    public Object[][] dpMethod(){
//        return new Object[][] {{"chrome"}, {"edge"}, {"none"}};
//    }

    @BeforeTest
    public void setUpTests(){
        Configuration.baseUrl = BASE_URL;
    }

    @BeforeClass
    @Parameters({"Brawser type"})
    public void setUpClass(String brawserType) {
        Configuration.browser = brawserType;
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        closeWebDriver();
    }

    @AfterTest
    public void tearDownTests(){
        System.out.println("All browsers were tested");
    }
}
