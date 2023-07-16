package api.enums;

public enum UserMethod {
    CREATE("createUser"),
    GET("getUser"),
    GET_BY_NAME("getUserByName"),
    GET_ALL("getAllUsers"),
    UPDATE("updateUser"),
    REMOVE("removeUser"),
    DISABLE("disableUser"),
    ENABLE("enableUser"),
    IS_ACTIVE("isActiveUser");

    private String name;

    UserMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
