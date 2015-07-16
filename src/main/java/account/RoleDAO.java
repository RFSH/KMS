package account;

import db.BaseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO extends BaseDAO<Role> {
    @Override
    public String getTableName() {
        return "roles";
    }

    @Override
    public String[] getColumnValues(Role role) {
        return new String[]{
                "id", role.getId(),
                "name", role.getName()
        };
    }

    @Override
    public String getWhereClause(Role role) {
        return "id='" + role.getId() + "'";
    }

    @Override
    public Role getObjectFromResult(ResultSet result) {
        Role role = new Role();
        try {
            role.setId(result.getString("id"));
            role.setName(result.getString("name"));
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
