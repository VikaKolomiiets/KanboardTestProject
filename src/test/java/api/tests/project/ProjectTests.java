package api.tests.project;

import api.enums.UserRole;
import api.steps.ProjectApiSteps;
import api.steps.UserApiSteps;
import api.tests.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class ProjectTests extends BaseTest {
    private static final String USERNAME = "Bossy";
    private static final String PASSWORD = "my_Pass";
    private static final String PROJECT_NAME ="Main project";
    private static final String IDENTIFIER = "PR20011";
    private static final Integer TASK_LIMIT = 5;
    private ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private UserApiSteps userApiSteps = new UserApiSteps();
    private DashboardPage dashboardPage;
    private String userId;

    @BeforeMethod
    public void setUp(){
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        boolean isManager = userApiSteps.updateUserRole(Integer.valueOf(userId), UserRole.APP_MANAGER);
        System.out.println(userId);
        this.dashboardPage = new LoginPage()
                .openLoginPage()
                .setUserNameInput(USERNAME)
                .setPasswordInput(PASSWORD)
                .openDashBoardPageByClickOnSubmitButton();
    }

    @Description("Positive")
    @Test
    public void testCreateNewProject(){
        this.dashboardPage.openNewProjectForm()
                .fillInNewProjectForm(PROJECT_NAME, IDENTIFIER, TASK_LIMIT)
                .clickSaveButtonNewProjectForm();
        String actualTitle = this.dashboardPage.getTitle().getText();
        String expectedTitle = PROJECT_NAME + " 0/" + IDENTIFIER;
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not contains project name.");
    }

    @AfterMethod
    public void tearDown(){
        boolean isRemovedUser = userApiSteps.removeUser(Integer.valueOf(userId));

    }
}
