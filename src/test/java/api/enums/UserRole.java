package api.enums;

public enum UserRole {
    APP_ADMIN("app-admin"),
    APP_MANAGER("app-manager"),
    APP_USER("app-user");
    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
