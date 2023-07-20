package api.enums;

public enum ProjectMethod {
    CREATE_PROJECT("createProject"),
    GET_PROJECT_BY_ID("getProjectById"),
    GET_PROJECT_BY_NAME("getProjectByName"),
    GET_PROJECT_BY_IDENTIFIER("getProjectByIdentifier"),
    GET_PROJECT_BY_EMAIL("getProjectByEmail"),
    GET_ALL_PROJECT("getAllProjects"),
    UPDATE_PROJECT("updateProject"),
    REMOVE_PROJECT("removeProject"),
    ENABLE_PROJECT("enableProject"),
    DISABLE_PROJECT("disableProject"),
    ENABLE_PROJECT_PUBLIC_ACCESS("enableProjectPublicAccess"),
    DISABLE_PROJECT_PUBLIC_ACCESS("disableProjectPublicAccess"),
    GET_PROJECT_ACTIVITY("getProjectActivity")

    ;

    private String name;

    ProjectMethod(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
