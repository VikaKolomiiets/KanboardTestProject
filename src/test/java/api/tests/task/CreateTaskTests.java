package api.tests.task;

import api.enums.UserRole;
import api.steps.ProjectApiSteps;
import api.steps.UserApiSteps;
import api.tests.BaseTest;
import api.utils.AddRandomDataTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ProjectPage;
import pages.ProjectsPage;

public class CreateTaskTests extends BaseTest {

    private static final String USERNAME = AddRandomDataTests.addUniqueSuffix("Bossy");
    private static final String PASSWORD = "my_Pass";
    private static final String PROJECT_NAME = AddRandomDataTests.addUniqueSuffix("Main project");
    private static final String TASK_NAME = AddRandomDataTests.addUniqueSuffix("The first task");
    private ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private UserApiSteps userApiSteps = new UserApiSteps();
    private DashboardPage dashboardPage;
    private String userId;
    private String projectId;


    @BeforeMethod
    public void setUpMethod() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        boolean isManager = userApiSteps.updateUserRole(Integer.valueOf(userId), UserRole.APP_MANAGER);
        System.out.println("UserId = " + userId);
        projectId = projectApiSteps.createProject(PROJECT_NAME, USERNAME, PASSWORD, Integer.valueOf(userId));
        System.out.println("Project Id = " + projectId);
        this.dashboardPage = new LoginPage().openDashboardPage(USERNAME, PASSWORD);
    }

    @Test
    public void testCreateTask() {
        ProjectsPage projectsPage = dashboardPage.clickOnProjectNumber(projectId)
                .openProjectsPage();
        Assert.assertEquals(projectsPage.getProjectsCount(), 1);
        ProjectPage projectPage = projectsPage
                .openDropDownInChosenProject(projectId)
                .clickConfigureToOpenProjectPage();
        boolean isExistBefore = projectPage.isContainTextInTableBody("1");
        boolean isExistAfter = projectPage.createNewTask(TASK_NAME).isContainTextInTableBody("1");
        Assert.assertNotEquals(isExistAfter, isExistBefore, "Task is not added.");

    }

    @AfterMethod
    public void tearDownMethod() {
        String projectId = projectApiSteps.getProjectIdByName(PROJECT_NAME, USERNAME, PASSWORD);
        boolean isRemovedProject = projectApiSteps.removeProject(Integer.valueOf(projectId), USERNAME, PASSWORD);
        boolean isRemovedUser = userApiSteps.removeUser(Integer.valueOf(userId));
    }
}
