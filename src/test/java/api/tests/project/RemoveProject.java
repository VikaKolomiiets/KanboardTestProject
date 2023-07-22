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

public class RemoveProject extends BaseTest {

    private static final String USERNAME = "Bossy";
    private static final String PASSWORD = "my_Pass";
    private static final String PROJECT_NAME ="Main project";
    private static final String IDENTIFIER = "PR20011";
    private static final Integer TASK_LIMIT = 5;
    private ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private UserApiSteps userApiSteps = new UserApiSteps();
    private DashboardPage dashboardPage;
    private String userId;
    private String actualTitle;         //for negative test
    private String projectId;
    @BeforeMethod
    public void setUpMethod(){
        userId = userApiSteps.createUser(USERNAME,  PASSWORD);
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
    public void testRemoveProject(){
        //System.out.println("My project");

        ProjectPage projectPage =  this.dashboardPage.clickOnProjectNumberIcon(projectId);
        ProjectsPage projectsPage =  projectPage.removeProjectByClickYesButton();

        Assert.assertTrue(projectPage.getTitle().getText().contains(PROJECT_NAME),
                "Project page is not opened by clicking on it's number.");
        Assert.assertTrue(
                projectsPage.getTitle().getText().contains("Projects"));
        Assert.assertEquals(projectsPage.getAlert().getText(),
                "Project removed successfully.",
                "Alert doesn't contain information about success.");
    }

    @AfterMethod
    public void tearDownMethod(){
        //for negative tests
        if(actualTitle.contains(PROJECT_NAME)){
            String projectId = projectApiSteps.getProjectIdByName(PROJECT_NAME, USERNAME, PASSWORD);
            boolean isRemovedProject = projectApiSteps.removeProject(Integer.valueOf(projectId),USERNAME, PASSWORD);
        }
        boolean isRemovedUser = userApiSteps.removeUser(Integer.valueOf(userId));
    }

}
