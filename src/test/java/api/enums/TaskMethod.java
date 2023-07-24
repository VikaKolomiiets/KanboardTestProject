package api.enums;

public enum TaskMethod {
    CREATE_TASK("createTask"),
    GET_TASK("getTask"),


    ;

    private String name;

    private TaskMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
