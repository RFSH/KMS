package knowledgebase;

import db.BaseDAO;

import java.sql.ResultSet;

public class LetterDAO extends BaseDAO<Letter>{
    @Override
    public String getTableName() {
        //TODO
        return null;
    }

    @Override
    public Object[] getColumnValues(Letter letter) {
        //TODO
        return new Object[0];
    }

    @Override
    public String getWhereClause(Letter letter) {
        //TODO
        return null;
    }

    @Override
    public Letter getObjectFromResult(ResultSet result) {
        //TODO
        return null;
    }
}
