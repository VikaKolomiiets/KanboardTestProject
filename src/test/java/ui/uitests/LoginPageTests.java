package ui.uitests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.DashboardPage;
import pages.LoginPage;
import ui.utils.DataForTests;


public class LoginPageTests {

    private final String DASHBOARD_TITLE_NAME = "KB Dashboard for admin";

    @BeforeMethod
    public void setUpMethod() {
        Selenide.open("https://localhost/login");
    }

    @Description("Positive")
    @Test(dataProviderClass = DataForTests.class, dataProvider = "correct-both-input-data")
    public void testLogInPositive(String userName, String password) {
        DashboardPage dashboardPage = new LoginPage()
                .setUserNameInput(userName)
                .setPasswordInput(password)
                .clickOnSubmitButton();

        Assert.assertEquals(DASHBOARD_TITLE_NAME,
                dashboardPage.getTitleContainerName(),
                "DashboardPage is not visible");
    }

}
