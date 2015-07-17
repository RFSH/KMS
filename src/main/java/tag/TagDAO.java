package tag;

import db.BaseDAO;
import knowledge.Knowledge;
import knowledge.WikiKnowledge;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDAO extends BaseDAO<Tag> {
    @Override
    public String getTableName() {
        return "tags";
    }

    @Override
    public Object[] getColumnValues(Tag tag) {
        return new Object[]{
                "id", tag.getId(),
                "name", tag.getName()
        };
    }

    @Override
    public String getWhereClause(Tag tag) {
        return "id='" + tag.getId() + "'";
    }

    @Override
    public Tag getObjectFromResult(ResultSet result) {
        Tag tag = new Tag();
        try {
            tag.setId(result.getString("id"));
            tag.setName(result.getString("name"));
        } catch (SQLException e) {
        }
        return tag;
    }

    public List<Tag> getKnowledgeTags(String knowledgeId) {
        ResultSet result = query("knowledge_tag JOIN tags ON knowledge_tag.tag_id=tags.id", null);
        List<Tag> tags = new ArrayList<>();
        try {
            while (result.next()) {
                tags.add(getObjectFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }
}
