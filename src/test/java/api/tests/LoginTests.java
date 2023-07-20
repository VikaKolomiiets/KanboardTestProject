package api.tests;

import api.steps.UserApiSteps;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTests extends BaseTest  {
    private static final String USERNAME = "Kate";
    private static final String PASSWORD = "myPass";

    UserApiSteps userApiSteps = new UserApiSteps();
    private String userId;

    @BeforeMethod
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        System.out.println(userId);
    }

    @Description("Positive")
    @Test
    public void testLoginByNewUser() {
        DashboardPage dashboardPage = new LoginPage()
                .openLoginPage()
                .setUserNameInput(USERNAME)
                .setPasswordInput(PASSWORD)
                .openDashBoardPageByClickOnSubmitButton();
        String actualPageTitleName = dashboardPage.getTitleContainerName();
                String expectedPageTitleName = "KB Dashboard for " + USERNAME.substring(0, 4);
        Assert.assertEquals(actualPageTitleName, expectedPageTitleName, "New user cannot open DashBoard");
    }
    @Description("Positive")
    @Test
    public void testLoginByNewUserAndLogOut() {
        DashboardPage dashboardPage = new LoginPage()
                .openLoginPage()
                .setUserNameInput(USERNAME)
                .setPasswordInput(PASSWORD)
                .openDashBoardPageByClickOnSubmitButton();
        String actualPageTitleName = dashboardPage.getTitleContainerName();
        String expectedPageTitleName = "KB Dashboard for " + USERNAME.substring(0, 4);
        Assert.assertEquals(actualPageTitleName, expectedPageTitleName, "New user cannot open DashBoard");
        boolean isOnLoginPage = dashboardPage.logOutFromDashboardPage().isExistOnLoginPage();
        Assert.assertTrue(isOnLoginPage, "User can not log out from DashboardPage.");

    }

    @Description("Negative")
    @Test
    public void testLoginByNewUserWithOutPassword(){
        boolean isPresentOnLoginPage = new LoginPage()
                .openLoginPage()
                .setUserNameInput(USERNAME)
                .clickOnSubmitButton()
                .isExistOnLoginPage();
        Assert.assertTrue(isPresentOnLoginPage, "User cannot get permission without PASSWORD");
    }

    @Description("Negative")
    @Test
    public void testLoginByNewUserWithAdminPassword(){
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
    public void testLoginWithOutAnyInputData(){
        boolean isPresentOnLoginPage = new LoginPage()
                .openLoginPage()
                .clickOnSubmitButton()
                .isExistOnLoginPage();
        Assert.assertTrue(isPresentOnLoginPage, "User cannot get permission without input data");
    }

    @AfterMethod(alwaysRun = true)
    public void removeUserAfterTest() {

        userApiSteps.removeUser(Integer.valueOf(userId));
    }
}
