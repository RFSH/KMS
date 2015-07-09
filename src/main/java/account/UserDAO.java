package account;

import db.BaseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO<User> {
    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public String[] getColumnValues(User user) {
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
        return "id='" + user.getId();
    }

    @Override
    public User getObjectFromResult(ResultSet result) {
        try {
            User user = new User();
            user.setId(result.getString("id"));
            user.setFirstName(result.getString("first_name"));
            user.setLastName(result.getString("last_name"));
            user.setUsername(result.getString("username"));
            user.setNationalId(result.getString("national_id"));
            user.setEmail(result.getString("email"));
            user.setPassword(result.getString("password"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
