package api.steps;

import api.args.user.CreateUser;
import api.args.user.UpdateUser;
import api.args.user.UserId;
import api.enums.UserMethod;
import api.enums.UserRole;
import api.models.BodyArgs;
import api.models.Results;
import io.restassured.response.Response;

import static api.utils.EnvProperties.API_TOKEN;
import static api.utils.EnvProperties.API_USERNAME;

public class UserApiSteps extends BaseApiSteps{
    private final String BASIC_AUTH_INPUT = "admin";
    public String createUser(String userName, String userPassword){
        CreateUser body = new CreateUser().builder()
                .username(userName)
                .password(userPassword)
                .email(userName + "_@gmail.com")
                .role(UserRole.APP_USER.getRole())
                .name(userName.substring(0, 4))
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .method(UserMethod.CREATE.getName())
                .params(body)
                .build();
        Response response = restAssurePost(BASIC_AUTH_INPUT, BASIC_AUTH_INPUT, bodyArgs);
        response.then().statusCode(200);
        Results result = response.as(Results.class);
        return result.getResult().toString();
    }
    public boolean removeUser(Integer id){
        UserId body = new UserId().builder()
                .user_id(id)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .method(UserMethod.REMOVE.getName())
                .params(body)
                .build();
        Response response = restAssurePost(BASIC_AUTH_INPUT, BASIC_AUTH_INPUT, bodyArgs);
        return (boolean) response.as(Results.class).getResult();
    }

    public boolean updateUserRole(Integer userId, UserRole role){
        UpdateUser body = new UpdateUser().builder()
                .id(userId)
                .role(role.getRole())
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .method(UserMethod.UPDATE.getName())
                .params(body)
                .build();
        Response response = restAssurePost(BASIC_AUTH_INPUT, BASIC_AUTH_INPUT, bodyArgs);
        response.then().statusCode(200);
        return (boolean)response.as(Results.class).getResult();
    }

}
