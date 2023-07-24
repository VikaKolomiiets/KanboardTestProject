package api.steps;

import api.args.project.CreateProject;
import api.args.task.CreateTask;
import api.enums.ProjectMethod;
import api.models.BodyArgs;
import api.models.Results;
import io.restassured.response.Response;

import java.time.LocalDate;

public class TaskApiSteps extends BaseApiSteps {
    public String createTask(String title, String projectId, String userId, String userName, String password){
        CreateTask body = new CreateTask().builder()
                .title(title)
                .project_id(Integer.valueOf(projectId))
                .owner_id(Integer.valueOf(userId))
                .date_started(LocalDate.now().plusWeeks(1).toString())
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .method(ProjectMethod.CREATE_PROJECT.getName())
                .params(body)
                .build();
        Response response = restAssurePost(userName, password, bodyArgs);
        response.then().statusCode(200);
        Results result = response.as(Results.class);
        return result.getResult().toString();
    }
}
