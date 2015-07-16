package permission;

import db.BaseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionLevelDAO extends BaseDAO<PermissionLevel> {
    @Override
    public String getTableName() {
        return "permission_levels";
    }

    @Override
    public Object[] getColumnValues(PermissionLevel permissionLevel) {
        return new Object[]{
                "id", permissionLevel.getId(),
                "name", permissionLevel.getName(),
                "order", permissionLevel.getOrder()
        };
    }

    @Override
    public String getWhereClause(PermissionLevel permissionLevel) {
        return "id='" + permissionLevel.getId() + "'";
    }

    @Override
    public PermissionLevel getObjectFromResult(ResultSet result) {
        PermissionLevel permissionLevel = new PermissionLevel();
        try {
            permissionLevel.setId(result.getString("id"));
            permissionLevel.setName(result.getString("name"));
            permissionLevel.setOrder(result.getInt("order"));
            return permissionLevel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
