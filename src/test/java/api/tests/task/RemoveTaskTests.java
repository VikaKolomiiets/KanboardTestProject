package api.tests.task;

import api.enums.UserRole;
import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import api.steps.UserApiSteps;
import api.tests.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ProjectPage;
import pages.ProjectsPage;

public class RemoveTaskTests extends BaseTest {
    private static final String USERNAME = "Bossy";
    private static final String PASSWORD = "my_Pass";
    private static final String PROJECT_NAME = "Main project";
    private static final String TASK_NAME = "The first task";
    private ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private UserApiSteps userApiSteps = new UserApiSteps();
    private TaskApiSteps taskApiSteps = new TaskApiSteps();
    private DashboardPage dashboardPage;
    private ProjectsPage projectsPage;
    private ProjectPage projectPage;
    private String userId;
    private String projectId;
    private String taskId;

    @BeforeMethod
    public void setUpMethod() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        boolean isManager = userApiSteps.updateUserRole(Integer.valueOf(userId), UserRole.APP_MANAGER);
        System.out.println("UserId = " + userId);
        projectId = projectApiSteps.createProject(PROJECT_NAME, USERNAME, PASSWORD, Integer.valueOf(userId));
        System.out.println("Project Id = " + projectId);
        taskId = taskApiSteps.createTask(
                TASK_NAME, Integer.valueOf(projectId), Integer.valueOf(userId), USERNAME, PASSWORD);
        System.out.println("Task Id = " + taskId);

        this.dashboardPage = new LoginPage()
                .openLoginPage()
                .setUserNameInput(USERNAME)
                .setPasswordInput(PASSWORD)
                .openDashBoardPageByClickOnSubmitButton();

        this.projectPage = this.dashboardPage
                .clickOnProjectNumber(projectId)
                .openProjectsPage()
                .openDropDownInChosenProject(projectId)
                .clickConfigureToOpenProjectPage();
    }

    @Test
    public void testRemoveTask() {
        System.out.println("Good");
    }


    @AfterMethod
    public void tearDownMethod() {

        String projectId = projectApiSteps.getProjectIdByName(PROJECT_NAME, USERNAME, PASSWORD);
        boolean isRemovedProject = projectApiSteps.removeProject(Integer.valueOf(projectId), USERNAME, PASSWORD);
        boolean isRemovedUser = userApiSteps.removeUser(Integer.valueOf(userId));
    }


}
