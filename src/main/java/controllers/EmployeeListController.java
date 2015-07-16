package controllers;

import account.Employee;
import account.Role;
import account.RoleCatalog;
import account.UserCatalog;
import javang.Scope;

import java.util.List;

public class EmployeeListController {
    @Scope
    public List<Employee> searchEmployees(String name, String roleId) {
        return UserCatalog.getInstance().findEmployees(name, roleId);
    }

    @Scope
    public List<Role> getRoles() {
        return RoleCatalog.getInstance().getRoles();
    }
}
