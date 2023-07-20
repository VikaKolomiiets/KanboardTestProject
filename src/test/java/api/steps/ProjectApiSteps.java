package api.steps;

import api.args.project.CreateProject;
import api.args.project.ProjectId;
import api.args.project.ResponseProject;
import api.enums.ProjectMethod;
import api.enums.UserMethod;
import api.models.BodyArgs;
import api.models.Results;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.time.LocalDate;

public class ProjectApiSteps extends BaseApiSteps {

    private final String BASIC_AUTH_INPUT = "admin";
    public String createProject(String projectName, Integer ownerId) {
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
        Response response = restAssurePost(BASIC_AUTH_INPUT, BASIC_AUTH_INPUT, bodyArgs);
        response.then().statusCode(200);
        Results result = response.as(Results.class);
        return result.getResult().toString();
    }

    public boolean removeProject(Integer projectId){
        ProjectId body = new ProjectId().builder()
                .project_id(projectId)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .method(ProjectMethod.REMOVE_PROJECT.getName())
                .params(body)
                .build();
        Response response = restAssurePost(BASIC_AUTH_INPUT, BASIC_AUTH_INPUT, bodyArgs);
        response.then().statusCode(200);
        return (boolean)response.as(Results.class).getResult();
    }
}
