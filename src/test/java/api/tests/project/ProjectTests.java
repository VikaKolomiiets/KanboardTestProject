package api.tests.project;

import api.enums.UserRole;
import api.steps.ProjectApiSteps;
import api.steps.UserApiSteps;
import api.tests.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class ProjectTests extends BaseTest {
    private static final String USERNAME = "Bossy Girl";
    private static final String PASSWORD = "It's_Pass";
    private ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private UserApiSteps userApiSteps = new UserApiSteps();
    private DashboardPage dashboardPage;
    private String userId;

    @BeforeMethod
    public void setUp(){
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        boolean isManager = userApiSteps.updateUserRole(Integer.valueOf(userId), UserRole.APP_MANAGER);
        System.out.println(userId);
        DashboardPage dashboardPage = new LoginPage()
                .openLoginPage()
                .setUserNameInput(USERNAME)
                .setPasswordInput(PASSWORD)
                .openDashBoardPageByClickOnSubmitButton();
    }

    @Description("Positive")
    @Test
    public void testCreateNewProject(){

    }

    @AfterMethod
    public void tearDown(){
        boolean isRemovedUser = userApiSteps.removeUser(Integer.valueOf(userId));

    }
}
