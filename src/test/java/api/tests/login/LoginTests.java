package api.tests.login;

import api.steps.UserApiSteps;
import api.tests.BaseTest;
import api.utils.AddRandomDataTests;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    private static final String USERNAME = AddRandomDataTests.addUniqueSuffix("Kate");
    private static final String PASSWORD = "myPass";

    public UserApiSteps userApiSteps = new UserApiSteps();
    private String userId;

    @BeforeMethod
    public void setUpMethod() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        System.out.println(userId);
    }

    @Description("Positive")
    @Test
    public void testLoginByNewUser() {
        DashboardPage dashboardPage = new LoginPage().openDashboardPage(USERNAME, PASSWORD);
        String actualPageTitleName = dashboardPage.getTitleContainerName();
        String expectedPageTitleName = "KB Dashboard for " + USERNAME.substring(0, 4);
        Assert.assertEquals(actualPageTitleName, expectedPageTitleName, "New user cannot open DashBoard");
    }

    @Description("Positive")
    @Test
    public void testLoginByNewUserAndLogOut() {
        DashboardPage dashboardPage = new LoginPage().openDashboardPage(USERNAME, PASSWORD);
        String actualPageTitleName = dashboardPage.getTitleContainerName();
        String expectedPageTitleName = "KB Dashboard for " + USERNAME.substring(0, 4);
        Assert.assertEquals(actualPageTitleName, expectedPageTitleName, "New user cannot open DashBoard");
        boolean isOnLoginPage = dashboardPage.logOutFromDashboardPage().isExistOnLoginPage();
        Assert.assertTrue(isOnLoginPage, "User can not log out from DashboardPage.");
    }

    @Description("Negative")
    @Test
    public void testLoginByNewUserWithOutPassword() {
        boolean isPresentOnLoginPage = new LoginPage()
                .openLoginPage()
                .setUserNameInput(USERNAME)
                .clickOnSubmitButton()
                .isExistOnLoginPage();
        Assert.assertTrue(isPresentOnLoginPage, "User cannot get permission without PASSWORD");
    }

    @Description("Negative")
    @Test
    public void testLoginByNewUserWithAdminPassword() {
        boolean isPresentOnLoginPage = new LoginPage()
                .openLoginPage()
                .setUserNameInput(USERNAME)
                .setPasswordInput("admin")
                .clickOnSubmitButton()
                .isExistOnLoginPage();
        Assert.assertTrue(isPresentOnLoginPage, "User cannot get permission with wrong PASSWORD");
    }

    @Description("Negative")
    @Test
    public void testLoginWithOutAnyInputData() {
        boolean isPresentOnLoginPage = new LoginPage()
                .openLoginPage()
                .clickOnSubmitButton()
                .isExistOnLoginPage();
        Assert.assertTrue(isPresentOnLoginPage, "User cannot get permission without input data");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod() {
        userApiSteps.removeUser(Integer.valueOf(userId));
    }
}
