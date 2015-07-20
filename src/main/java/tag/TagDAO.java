package tag;

import db.BaseDAO;

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
        ResultSet result = query("knowledge_tag JOIN tags ON knowledge_tag.tag_id=tags.id", "knowledge_id", knowledgeId);
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

    public void insertKnowledgeTags(String knowledgeId, List<Tag> tags) {
        for (Tag tag : tags) {
            ResultSet resultSet = query("knowledge_tag", "knowledge_id='" + knowledgeId + "' AND tag_id='" + tag.getId() + "'" );
            try {
                if (! resultSet.next()) {
                    insert("knowledge_tag", new Object[]{
                            "knowledge_id", knowledgeId,
                            "tag_id", tag.getId()
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
