package api.steps;

import api.args.task.CreateTask;
import api.args.task.TaskId;
import api.enums.TaskMethod;
import api.models.BodyArgs;
import api.models.Results;
import io.restassured.response.Response;
import org.apache.groovy.util.Arrays;

import java.time.LocalDate;

public class TaskApiSteps extends BaseApiSteps {
    public String createTask(String taskName, Integer projectId, Integer userId, String userName, String password) {
        CreateTask body = new CreateTask().builder()
                .title(taskName)
                .project_id(projectId)
                .owner_id(userId)
                .swimlane_id(0)
                .priority(0)
                .column_id(0)
                .tags(new String[0])
                .date_started(LocalDate.now().plusWeeks(1).toString())
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .method(TaskMethod.CREATE_TASK.getName())
                .params(body)
                .build();
        Response response = restAssurePost(userName, password, bodyArgs);
        response.then().statusCode(200);
        Results result = response.as(Results.class);
        return result.getResult().toString();
    }

    public boolean removeTask(Integer taskId, String userName, String password) {
        return actionOnTask(taskId, TaskMethod.REMOVE_TASK, userName, password);
    }

    public boolean openTask(Integer taskId, String userName, String password) {
        return actionOnTask(taskId, TaskMethod.OPEN_TASK, userName, password);
    }

    public boolean closeTask(Integer taskId, String userName, String password) {
        return actionOnTask(taskId, TaskMethod.CLOSE_TASK, userName, password);
    }

    public boolean actionOnTask(Integer taskId, TaskMethod method, String userName, String password) {
        TaskId body = new TaskId().builder()
                .task_id(taskId)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .method(method.getName())
                .params(body)
                .build();
        Response response = restAssurePost(userName, password, bodyArgs);
        response.then().statusCode(200);
        return (boolean) response.as(Results.class).getResult();
    }

}
