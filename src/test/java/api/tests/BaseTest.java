package api.tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static api.utils.EnvProperties.BASE_URL;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class BaseTest {
    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = BASE_URL;
    }

    @AfterClass(alwaysRun = true)
    public void cleanup() {
        closeWebDriver();
    }
}
