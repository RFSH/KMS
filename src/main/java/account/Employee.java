package account;

import permission.PermissionLevel;
import util.Strings;
import util.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User {
    private Role role;
    private PermissionLevel permissionLevel;

    public void validate() throws ValidationError {
        super.validate();

        List<String> errors = new ArrayList<String>();
        if (role == null) {
            errors.add(Strings.USER_NO_ROLE);
        }
        if (permissionLevel == null) {
            errors.add(Strings.USER_NO_PERMISSION);
        }
        if (!errors.isEmpty()) {
            throw new ValidationError(errors);
        }
    }

    /* Getters and Setters */

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(PermissionLevel permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
}
