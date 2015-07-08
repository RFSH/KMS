package account;

import java.util.List;

public class UserCatalog {
    private static UserCatalog instance;

    private UserCatalog() {

    }

    public Employee addEmployee(String firstName, String lastName, String username, String email,
                            String nationalId, String password, Role role) {
        return null;
    }

    public void deleteEmployee(Employee employee) {

    }

    public User findUser(String username) {
        return new UserDAO().getObject("username", username);
    }

    public Employee findEmployee(String username) {
        return null;
    }

    public List<Employee> getEmployees() {
        return null;
    }

    public List<Employee> findEmployees() {
        return null;
    }

    public static UserCatalog getInstance() {
        if (instance == null) {
            instance = new UserCatalog();
        }
        return instance;
    }
}
