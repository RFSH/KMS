package knowledgebase;

import db.BaseDAO;

import java.sql.ResultSet;

public class ProjectDAO extends BaseDAO<Project>{

    @Override
    public String getTableName() {
        //TODO
        return null;
    }

    @Override
    public Object[] getColumnValues(Project project) {
        //TODO
        return new Object[0];
    }

    @Override
    public String getWhereClause(Project project) {
        //TODO
        return null;
    }

    @Override
    public Project getObjectFromResult(ResultSet result) {
        //TODO
        return null;
    }
}
