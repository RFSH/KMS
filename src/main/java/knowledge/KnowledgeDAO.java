package knowledge;

import db.BaseDAO;

import java.sql.ResultSet;

public class KnowledgeDAO extends BaseDAO<Knowledge>{
    @Override
    public String getTableName() {
        //TODO
        return null;
    }

    @Override
    public Object[] getColumnValues(Knowledge knowledge) {
        //TODO
        return new Object[0];
    }

    @Override
    public String getWhereClause(Knowledge knowledge) {
        //TODO
        return null;
    }

    @Override
    public Knowledge getObjectFromResult(ResultSet result) {
        //TODO
        return null;
    }
}
