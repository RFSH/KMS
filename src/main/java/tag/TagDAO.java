package tag;

import db.BaseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagDAO extends BaseDAO<Tag> {
    @Override
    public String getTableName() {
        return "tags";
    }

    @Override
    public Object[] getColumnValues(Tag tag) {
        return new String[]{
                "id", tag.getId(),
                "name", tag.getName()
        };
    }

    @Override
    public String getWhereClause(Tag tag) {
        return "id='" + tag.getId();
    }


    private void populateTag(ResultSet result, Tag tag) throws SQLException {
        tag.setId(result.getString("id"));
        tag.setName(result.getString("name"));
    }

    @Override
    public Tag getObjectFromResult(ResultSet result) {
        Tag tag = new Tag();
        try {
            populateTag(result, tag);
            return tag;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
