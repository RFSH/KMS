package knowledge;

import db.BaseDAO;

import java.sql.ResultSet;

public class VoteDAO extends BaseDAO<Vote> {
    @Override
    public String getTableName() {
        return "votes";
    }

    @Override
    public Object[] getColumnValues(Vote vote) {
        //TODO
        return new Object[0];
    }

    @Override
    public String getWhereClause(Vote vote) {
        //TODO
        return null;
    }

    @Override
    public Vote getObjectFromResult(ResultSet result) {
        //TODO
        return null;
    }
}
