package ui.uitests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.DashboardPage;
import pages.LoginPage;
import ui.utils.DataForTests;


public class LoginPageTests {

    private final String DASHBOARD_TITLE_NAME = "KB Dashboard for admin";
    private final String ALERT_MESSAGE_TEXT = " ";

    @BeforeMethod
    public void setUpMethod() {
        Selenide.open("https://localhost/login");
    }

    @Description("Positive")
    @Test(dataProviderClass = DataForTests.class, dataProvider = "correct-both-input-data")
    public void testLoginPagePositive(String userName, String password) {
        DashboardPage dashboardPage = new LoginPage()
                .setUserNameInput(userName)
                .setPasswordInput(password)
                .openDashBoardPageByClickOnSubmitButton();

        Assert.assertEquals(DASHBOARD_TITLE_NAME,
                dashboardPage.getTitleContainerName(),
                "DashboardPage is not visible");
    }

    @Description("Negative")
    @Test
    public void testAfterClickButtonWithoutAnyDataInput(){
        LoginPage loginPage = new LoginPage().clickOnSubmitButton();

        Assert.assertTrue(
                loginPage.isExistOnLoginPage(),
                "Login page doesn't exist after clicking button without fill in data");
    }

    @Description("Negative")
    @Parameters()
    @Test(dataProviderClass = DataForTests.class, dataProvider = "input-one-data")
    public void testAfterButtonClickWithOnlyUserNameInput(String name){
        LoginPage loginPage = new LoginPage()
                .setUserNameInput(name)
                .clickOnSubmitButton();
        Assert.assertTrue(
                loginPage.isExistOnLoginPage(),
                "Login page doesn't exist after clicking button with incorrect fill in data");
    }
    @Description("Negative")
    @Parameters()
    @Test(dataProviderClass = DataForTests.class, dataProvider = "input-one-data")
    public void testAfterButtonClickWithOnlyPasswordInput(String name){
        LoginPage loginPage = new LoginPage()
                .setPasswordInput(name)
                .clickOnSubmitButton();
        Assert.assertTrue(
                loginPage.isExistOnLoginPage(),
                "Login page doesn't exist after clicking button with incorrect fill in data");
    }

    @Description("Negative")
    @Test(dataProviderClass = DataForTests.class, dataProvider = "incorrect-both-input-data")
    public void testLoginPageIncorrectBothDataInput(String userName, String password) {
        LoginPage loginPage = new LoginPage()
                .setUserNameInput(userName)
                .setPasswordInput(password)
                .clickOnSubmitButton();
        Assert.assertTrue(loginPage.isExistOnLoginPage(),
                "Login page doesn't exist after clicking button with incorrect fill in data");
    }
    @Description("Positive")
    @Test
    public void testForgotPasswordLink(){
        Assert.assertTrue(new LoginPage().clickOnForgetPassword(),
                "There is not opened new page for input data if click on forgot password.");

    }

}
