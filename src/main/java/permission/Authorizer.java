package permission;

import account.Employee;
import account.Manager;
import account.User;

public class Authorizer {
    public boolean canChangeUser(User user) {
        return false;
    }

    public boolean isAuthorized(Employee employee, PermissionLevel permissionLevel) {
        return false;
    }

    public boolean isAuthorized(Manager manager, PermissionLevel permissionLevel) {
        return true;
    }
}
