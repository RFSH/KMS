package controllers;

import account.Employee;
import account.Role;
import account.RoleCatalog;
import account.UserCatalog;
import javang.JavaNGController;
import javang.Scope;
import netscape.javascript.JSObject;
import permission.PermissionLevel;
import permission.PermissionLevelCatalog;
import util.ValidationError;

import java.util.List;

public class AddOrEditEmployeeController extends JavaNGController {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String nationalId;
    private String password;
    private String passwordConfirm;
    private Role role;
    private PermissionLevel permission;

    @Scope
    public String addOrUpdateEmployee(boolean update, JSObject details) {
        readEmployeeValues(details);

        try {
            if (password != null && !password.isEmpty() && !password.equals(passwordConfirm)) {
                throw new ValidationError("رمز عبور‌ها مطابقت ندارند");
            }

            if (update) {
                updateEmployee();
            } else {
                addEmployee();
            }
        } catch (ValidationError validationError) {
            return validationError.getError();
        }

        changePage("/employee/list");
        return "";
    }

    @Scope
    public List<Role> getRoles() {
        return RoleCatalog.getInstance().getRoles();
    }

    @Scope
    public List<PermissionLevel> getPermissionLevels() {
        return PermissionLevelCatalog.getInstance().getOrderedPermissionLevels();
    }

    private void updateEmployee() throws ValidationError {
        Employee user = UserCatalog.getInstance().findEmployeeById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setNationalId(nationalId);
        user.setPassword(password);
        user.setRole(role);
        user.setPermissionLevel(permission);
        UserCatalog.getInstance().updateUser(user);
    }

    private void addEmployee() throws ValidationError {
        UserCatalog.getInstance().addEmployee(firstName, lastName, username, email, nationalId, password, role, permission);
    }

    private void readEmployeeValues(JSObject details) {
        id = getMember(details, "id");
        firstName = getMember(details, "firstName");
        lastName = getMember(details, "lastName");
        username = getMember(details, "username");
        email = getMember(details, "email");
        nationalId = getMember(details, "nationalId");
        password = getMember(details, "password");
        passwordConfirm = getMember(details, "passwordConfirm");
        String roleId = getMember(details, "role");
        String permissionId = getMember(details, "permissionLevel");
        role = RoleCatalog.getInstance().findById(roleId);
        permission = PermissionLevelCatalog.getInstance().findById(permissionId);
    }

    private String getMember(JSObject details, String key) {
        String value = (String) details.getMember(key);
        if (value.equals("undefined")) {
            return null;
        }
        return value;
    }
}
