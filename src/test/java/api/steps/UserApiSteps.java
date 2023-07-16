package api.steps;

import api.args.user.CreateUser;
import api.args.user.ResultUser;
import api.args.user.UpdateUser;
import api.args.user.UserId;
import api.enums.UserMethod;
import api.enums.UserRole;
import api.models.BodyArgs;
import api.models.Result;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserApiSteps extends BaseApiSteps{

    public String createUser(String userName, String userPassword){
        CreateUser body = new CreateUser().builder()
                .username(userName)
                .password(userPassword)
                .email(userName + "_@gmail.com")
                .role(UserRole.APP_USER)
                .name(userName.substring(0, 4))
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .method(UserMethod.CREATE.getName())
                .params(body)
                .build();
        Response response = restAssurePost(userName, userPassword, bodyArgs);
        response.then().statusCode(200);
        Result result = response.as(Result.class);
        return result.getResult().toString();
    }
    public void removeUser(Integer id){
        BodyArgs bodyArgs = BodyArgs.builder()
                .method(UserMethod.REMOVE.getName())
                .params(new UserId(id))
                .build();
        Response response = restAssurePost()
    }

}
