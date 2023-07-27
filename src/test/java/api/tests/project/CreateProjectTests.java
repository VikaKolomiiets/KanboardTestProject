package api.tests.project;

import api.enums.UserRole;
import api.steps.ProjectApiSteps;
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

public class CreateProjectTests extends BaseTest {
    private static final String USERNAME = AddRandomDataTests.addUniqueSuffix("Bossy");
    private static final String PASSWORD = "my_Pass";
    private static final String PROJECT_NAME = AddRandomDataTests.addUniqueSuffix("Main project");
    private static final String IDENTIFIER = "PR20011";
    private static final Integer TASK_LIMIT = 5;
    private static final String ERROR_TEXT = "The project name is required";
    private ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private UserApiSteps userApiSteps = new UserApiSteps();
    private DashboardPage dashboardPage;
    private String userId;
    private String actualTitle;

    @BeforeMethod
    public void setUpMethod(){
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        boolean isManager = userApiSteps.updateUserRole(Integer.valueOf(userId), UserRole.APP_MANAGER);
        System.out.println(userId);
        this.dashboardPage = new LoginPage().openDashboardPage(USERNAME, PASSWORD);
    }

    @Description("Positive")
    @Test
    public void testCreateNewProject(){
        this.dashboardPage.openNewProjectForm()
                .fillNewProjectForm(PROJECT_NAME, IDENTIFIER, TASK_LIMIT)
                .clickSaveButtonNewProjectForm();
        actualTitle = this.dashboardPage.getTitleContainerName();

        Assert.assertTrue(actualTitle.contains(PROJECT_NAME),
                "Title does not contain project name.");
        Assert.assertTrue(actualTitle.contains(TASK_LIMIT.toString()),
                "Title does not contain Task limit number.");
    }

    @Description("Negative")
    @Test
    public void testCreateCancelNewProject(){
        this.dashboardPage.openNewProjectForm()
                .fillNewProjectForm(PROJECT_NAME, IDENTIFIER, TASK_LIMIT)
                .clickCancelInNewProjectForm();
        actualTitle = this.dashboardPage.getTitle().getText();

        Assert.assertFalse(actualTitle.contains(PROJECT_NAME),
                "Title does not contain project name.");
        Assert.assertFalse(actualTitle.contains(TASK_LIMIT.toString()),
                "Title does not contain Task limit number.");
    }

    @Description("Negative")
    @Test
    public void testCreateNewProjectWithOutNameInput(){
        this.dashboardPage.openNewProjectForm()
                .clickSaveButtonNewProjectForm();
        actualTitle = this.dashboardPage.getTitle().getText();
        String actualErrorMessage = this.dashboardPage.getErrorProjectFormText();
        Assert.assertTrue(actualErrorMessage != null, "Message does not exist.");
        Assert.assertEquals(actualErrorMessage, ERROR_TEXT, "Message is not correct.");
    }

    @AfterMethod
    public void tearDownMethod(){
        if(actualTitle.contains(PROJECT_NAME)){
            String projectId = projectApiSteps.getProjectIdByName(PROJECT_NAME, USERNAME, PASSWORD);
            boolean isRemovedProject = projectApiSteps.removeProject(Integer.valueOf(projectId),USERNAME, PASSWORD);
        }
        boolean isRemovedUser = userApiSteps.removeUser(Integer.valueOf(userId));
    }
}
