package api.enums;

public enum TaskColumn {
    BACKLOG("Backlog"),
    READY("Ready"),
    WORK_IN_PROGRESS("Work in progress"),
    DONE("Done");
    private String name;

    private TaskColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
