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
import pages.ProjectPage;
import pages.ProjectsPage;

public class RemoveProjectTests extends BaseTest {

    private static final String USERNAME = "Bossy";
    private static final String PASSWORD = "my_Pass";
    private static final String PROJECT_NAME = "Main project";
    private static final String IDENTIFIER = "PR20011";
    private static final String ALERT_NO_PROJECT = "There is no project.";
    private static final Integer TASK_LIMIT = 5;
    private ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private UserApiSteps userApiSteps = new UserApiSteps();
    private DashboardPage dashboardPage;
    private String userId;
    private String actualTitle;         //for negative test
    private String projectId;
    private boolean isRemoved = false;

    @BeforeMethod
    public void setUpMethod() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        boolean isManager = userApiSteps.updateUserRole(Integer.valueOf(userId), UserRole.APP_MANAGER);
        System.out.println("UserId = " + userId);
        projectId = projectApiSteps.createProject(PROJECT_NAME, USERNAME, PASSWORD, Integer.valueOf(userId));
        System.out.println("Project Id = " + projectId);
        this.dashboardPage = new LoginPage()
                .openLoginPage()
                .setUserNameInput(USERNAME)
                .setPasswordInput(PASSWORD)
                .openDashBoardPageByClickOnSubmitButton();
        actualTitle = this.dashboardPage.getTitle().getText();
    }

    @Description("Positive: Remove project by owner of this project.")
    @Test
    public void testRemoveProject() {
        ProjectsPage projectsPage = dashboardPage.clickOnProjectNumber(projectId)
                .openProjectsPage();
        Assert.assertEquals(projectsPage.getProjectsCount(), 1);
        ProjectPage projectPage = projectsPage
                .openDropDownInChosenProject(projectId)
                .clickConfigureToOpenProjectPage();

        Assert.assertTrue(projectPage.getTitle().getText().contains(PROJECT_NAME),
                "Project page is not opened by clicking on it in Main page.");

        projectsPage = projectPage.removeProject(projectId);
        String actualAlert = projectsPage.getTextFromAlertNoProject();

        isRemoved = actualAlert.contains(ALERT_NO_PROJECT);
        Assert.assertEquals(actualAlert, ALERT_NO_PROJECT,
                "Project is not removed");
    }

    @AfterMethod
    public void tearDownMethod() {
        boolean isRemovedUser = userApiSteps.removeUser(Integer.valueOf(userId));
    }
}
