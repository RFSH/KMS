package knowledgebase;

import db.BaseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectActivityDAO extends BaseDAO<ProjectActivity>{
    @Override
    public String getTableName() {
        return "project_activities";
    }

    @Override
    public Object[] getColumnValues(ProjectActivity projectActivity) {
        return new String[]{
                "id", projectActivity.getId(),
                "project_id", projectActivity.getProject().getId(),
                "title", projectActivity.getTitle(),
                "description", projectActivity.getDescription(),
        };
    }

    @Override
    public String getWhereClause(ProjectActivity projectActivity) {
        return "id='" + projectActivity.getId() + "'";
    }

    @Override
    public ProjectActivity getObjectFromResult(ResultSet result) {
        ProjectActivity projectActivity = new ProjectActivity();
        try{
            projectActivity.setId(result.getString("id"));
            projectActivity.setProject(ProjectCatalog.getInstance().findProjectById(result.getString("project_id")));
            projectActivity.setTitle(result.getString("title"));
            projectActivity.setDescription(result.getString("description"));
        }catch( SQLException e){
            e.printStackTrace();
        }
        return projectActivity;
    }

    public List<ProjectActivity> getProjectActivities(String projectId){
        ResultSet result = query("project_activities", "project_id", projectId);
        List<ProjectActivity> projectActivities = new ArrayList<>();
        try{
            while(result.next()){
                projectActivities.add(getObjectFromResult(result));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return projectActivities;
    }
}
