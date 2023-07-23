package api.steps;

import api.args.project.*;
import api.enums.ProjectMethod;
import api.models.BodyArgs;
import api.models.Results;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ProjectApiSteps extends BaseApiSteps {

    private final String BASIC_AUTH_INPUT = "admin";
    public String createProject(String projectName, String userName, String password, Integer ownerId) {
        CreateProject body = new CreateProject().builder()
                .name(projectName)
                .description("")
                .owner_id(ownerId)
                .start_date(LocalDate.now().toString())
                .end_date(LocalDate.now().plusMonths(1).toString())
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

    public boolean removeProject(Integer projectId, String userName, String password){
        ProjectId body = new ProjectId().builder()
                .project_id(projectId.toString())
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .method(ProjectMethod.REMOVE_PROJECT.getName())
                .params(body)
                .build();
        Response response = restAssurePost(userName, password, bodyArgs);
        response.then().statusCode(200);
        return (boolean)response.as(Results.class).getResult();
    }

    public String getProjectIdByName(String projectName, String userName, String password){
        GetProject body = new GetProject().builder()
                .name(projectName)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .method(ProjectMethod.GET_PROJECT_BY_NAME.getName())
                .params(body)
                .build();
        Response response = restAssurePost(userName, password, bodyArgs);

        Results result = response.as(Results.class);
        String s = ((LinkedHashMap) result.getResult()).get("id").toString();
        return s;
    }

}
