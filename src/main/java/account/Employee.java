package account;

public class Employee extends User {
    private Role role;

    public boolean validate() {
        return false;
    }

    /* Getters and Setters */

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
