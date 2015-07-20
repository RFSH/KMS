package knowledgebase;

import db.BaseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProjectDAO extends BaseDAO<Project>{

    @Override
    public String getTableName() {
        return "projects";
    }

    @Override
    public Object[] getColumnValues(Project project) {
        return new Object[]{
                "id", project.getId(),
                "title", project.getTitle(),
                "description", project.getDescription(),
                "creation_date", project.getCreationDate().getTime()
        };
    }

    public String getWhereClause(Project project) {
        return "id= '"+ project.getId()+ "'";
    }

    @Override
    public Project getObjectFromResult(ResultSet result) {
        Project project = new Project();
        try{
            project.setId(result.getString("id"));
            project.setTitle(result.getString("title"));
            project.setDescription(result.getString("description"));
            project.setCreationDate(new Date(result.getLong("creation_date")));
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return project;
    }

}
