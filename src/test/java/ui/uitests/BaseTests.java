package ui.uitests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTests {
    @BeforeClass
    public void setUpClass(){
        Configuration.browser = "chrome";
        Configuration.browserSize="2048x1019";
    }



    @AfterClass
    public void tearDownClass(){
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}
