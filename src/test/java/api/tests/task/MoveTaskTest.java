package api.tests.task;

import api.enums.UserRole;
import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import api.steps.UserApiSteps;
import api.tests.BaseTest;
import api.utils.DataTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ProjectPage;
import pages.TaskPage;

public class MoveTaskTest extends BaseTest {

    private static final String USERNAME = DataTests.addUniqueSuffix("Bossy");
    private static final String PASSWORD = "my_Pass";
    private static final String FROM_PROJECT_NAME = DataTests.addUniqueSuffix("First project");
    private static final String TO_PROJECT_NAME = DataTests.addUniqueSuffix("Third project");
    private static final String TASK_NAME = DataTests.addUniqueSuffix("Moving task");
    private ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private UserApiSteps userApiSteps = new UserApiSteps();
    private TaskApiSteps taskApiSteps = new TaskApiSteps();
    private DashboardPage dashboardPage;
    private String userId;
    private String fromProjectId;
    private String toProjectId;
    private String taskId;

    @BeforeMethod
    public void setUpMethod() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        boolean isManager = userApiSteps.updateUserRole(Integer.valueOf(userId), UserRole.APP_MANAGER);
        System.out.println("UserId = " + userId);
        fromProjectId = projectApiSteps.createProject(FROM_PROJECT_NAME, USERNAME, PASSWORD, Integer.valueOf(userId));
        System.out.println("Project Id = " + fromProjectId);
        toProjectId = projectApiSteps.createProject(TO_PROJECT_NAME, USERNAME, PASSWORD, Integer.valueOf(userId));
        System.out.println("Project Id = " + toProjectId);
        taskId = taskApiSteps.createTask(
                TASK_NAME, Integer.valueOf(fromProjectId), Integer.valueOf(userId), USERNAME, PASSWORD);
        System.out.println("Task Id = " + taskId);

        this.dashboardPage = new LoginPage()
                .openLoginPage()
                .setUserNameInput(USERNAME)
                .setPasswordInput(PASSWORD)
                .openDashBoardPageByClickOnSubmitButton();
    }

    @Test
    public void testMoveTaskToAnotherProjectSameOwner() {
        String actualTitle = this.dashboardPage.getMoveTaskForm(taskId).getTitleMoveTaskForm();
        String expectedTitle = "Move the task to another project";
        Assert.assertEquals(actualTitle, expectedTitle, "Move Form is not opened.");
        TaskPage taskPage = this.dashboardPage
                .chooseAnotherProjectForTest(TO_PROJECT_NAME)
                .clickSaveButtonAndOpenTaskPage();
        String actualProjectName = taskPage.getTitle().getText();
        Assert.assertEquals(actualProjectName, TO_PROJECT_NAME, "Containing task Project is not changed.");
        String actualTaskName = taskPage.getTaskNameOnProjectPage();
        Assert.assertEquals(actualTaskName, TASK_NAME, "Task is not exist on new Project Page");
    }


    @AfterMethod
    public void tearDownMethod() {
        String fromProjectId = projectApiSteps.getProjectIdByName(FROM_PROJECT_NAME, USERNAME, PASSWORD);
        boolean isRemovedFromProject = projectApiSteps.removeProject(Integer.valueOf(fromProjectId), USERNAME, PASSWORD);
        String toProjectId = projectApiSteps.getProjectIdByName(TO_PROJECT_NAME, USERNAME, PASSWORD);
        boolean isRemovedToProject = projectApiSteps.removeProject(Integer.valueOf(toProjectId), USERNAME, PASSWORD);
        boolean isRemovedUser = userApiSteps.removeUser(Integer.valueOf(userId));
    }
}
