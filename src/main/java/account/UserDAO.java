package account;

import db.BaseDAO;
import permission.PermissionLevel;
import permission.PermissionLevelCatalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO<User> {
    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public Object[] getColumnValues(User user) {
        return new String[] {
                "id", user.getId(),
                "first_name", user.getUsername(),
                "last_name", user.getLastName(),
                "username", user.getUsername(),
                "email", user.getEmail(),
                "national_id", user.getNationalId(),
                "password", user.getPassword()
        };
    }

    @Override
    public String getWhereClause(User user) {
        return "id='" + user.getId() + "'";
    }

    private void populateUser(ResultSet result, User user) throws SQLException {
        user.setId(result.getString("id"));
        user.setFirstName(result.getString("first_name"));
        user.setLastName(result.getString("last_name"));
        user.setUsername(result.getString("username"));
        user.setNationalId(result.getString("national_id"));
        user.setEmail(result.getString("email"));
        user.setPassword(result.getString("password"));
    }

    @Override
    public User getObjectFromResult(ResultSet result) {
        User user = new User();
        try {
            populateUser(result, user);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Employee getEmployeeFromResult(ResultSet result) {
        Employee user = new Employee();
        try {
            populateUser(result, user);
            user.setRole(RoleCatalog.getInstance().findById(result.getString("role_id")));
            user.setPermissionLevel(PermissionLevelCatalog.getInstance().findById(result.getString("permission_id")));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Manager getManagerFromResult(ResultSet result) {
        Manager user = new Manager();
        try {
            populateUser(result, user);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Employee getEmployee(String column, String value) {
        ResultSet result = query("users JOIN employees ON users.id=employees.id", "users."+column, value);
        try {
            if (result.next()) {
                return getEmployeeFromResult(result);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Employee> getEmployees(String column, String value) {
        ResultSet result = query("users JOIN employees ON users.id=employees.id", column, value);
        List<Employee> employees = new ArrayList<>();
        try {
            while (result.next()) {
                employees.add(getEmployeeFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<Employee> getEmployees(String queryStr) {
        ResultSet result = query("users JOIN employees ON users.id=employees.id", queryStr);
        List<Employee> employees = new ArrayList<>();
        try {
            while (result.next()) {
                employees.add(getEmployeeFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<Employee> getEmployees() {
        ResultSet result = query("users JOIN employees ON users.id=employees.id", null);
        List<Employee> employees = new ArrayList<>();
        try {
            while (result.next()) {
                employees.add(getEmployeeFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Manager getManager(String column, String value) {
        ResultSet result = query("users JOIN managers ON users.id=managers.id", "users."+column, value);
        try {
            if (result.next()) {
                return getManagerFromResult(result);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insert(Employee employee) {
        super.insert(employee);

        String roleId = employee.getRole() == null ? null : employee.getRole().getId();
        String permissionId = employee.getPermissionLevel() == null ? null : employee.getPermissionLevel().getId();

        super.insert("employees", new String[] {
                "id", employee.getId(),
                "role_id", roleId,
                "permission_id", permissionId
        });
    }

    public void insert(Manager manager) {
        super.insert(manager);

        super.insert("managers", new String[] {
                "id", manager.getId()
        });
    }

    public void update(Employee employee) {
        super.update(employee);

        String roleId = employee.getRole() == null ? null : employee.getRole().getId();
        String permissionId = employee.getPermissionLevel() == null ? null : employee.getPermissionLevel().getId();

        super.update("employees", getWhereClause(employee), new String[] {
                "id", employee.getId(),
                "role_id", roleId,
                "permission_id", permissionId
        });
    }

    public void update(Manager manager) {
        super.update(manager);

        super.update("managers", getWhereClause(manager), new String[]{
                "id", manager.getId()
        });
    }
}
