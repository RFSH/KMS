package account;

import permission.PermissionLevel;
import util.IdGenerator;
import util.ValidationError;

import java.util.List;
import java.util.UUID;

public class UserCatalog {
    private static UserCatalog instance;

    private UserCatalog() {

    }

    public Employee addEmployee(String firstName, String lastName, String username, String email,
                            String nationalId, String password, Role role, PermissionLevel permissionLevel) throws ValidationError {
        Employee employee = new Employee();
        employee.setId(IdGenerator.generateID());
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setUsername(username);
        employee.setEmail(email);
        employee.setNationalId(nationalId);
        employee.setRole(role);
        employee.setPermissionLevel(permissionLevel);
        employee.setPassword(password);

        employee.validate();
        new UserDAO().insert(employee);
        return employee;
    }

    public void deleteEmployee(Employee employee) {

    }

    public User findUser(String username) {
        return new UserDAO().getObject("username", username);
    }

    public Employee findEmployeeByUsername(String username) {
        return new UserDAO().getEmployee("username", username);
    }

    public Employee findEmployeeById(String id) {
        return new UserDAO().getEmployee("id", id);
    }

    public List<Employee> getEmployees() {
        return null;
    }

    public List<Employee> findEmployees() {
        return null;
    }

    public void updateUser(User user) throws ValidationError {
        user.validate();
        new UserDAO().update(user);
    }

    public static UserCatalog getInstance() {
        if (instance == null) {
            instance = new UserCatalog();
        }
        return instance;
    }
}
