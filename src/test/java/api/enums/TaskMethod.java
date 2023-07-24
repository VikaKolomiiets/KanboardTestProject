package api.enums;

public enum TaskMethod {
    CREATE_TASK("createTask"),
    GET_TASK("getTask"),
    GET_TAST_BY_REFERENCE("getTaskByReference"),
    GET_ALL_TASKS("getAllTasks"),
    GET_OVERDUE_TASKS("getOverdueTasks"),
    GET_OVERDUE_TASKS_BY_Project("getOverdueTasksByProject"),
    UPDATE_TASK("updateTask"),
    OPEN_TASK("openTask"),
    CLOSE_TASL("closeTask"),
    REMOVE_TASK("removeTask"),
    MOVE_TASK_POSITION("moveTaskPosition"),
    MOVE_TASK_TO_PROJECT("moveTaskToProject"), //Move a task to another project
    DUPLICATE_TASK_TO_PROJECT ("duplicateTaskToProject"), //Move a task to another column or another position
    SEARCH_TASK("searchTasks");

    private String name;

    private TaskMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
