package report;

import db.BaseDAO;

import java.sql.ResultSet;

public class AbuseReportDAO extends BaseDAO<AbuseReport> {
    @Override
    public String getTableName() {
        //TODO
        return null;
    }

    @Override
    public Object[] getColumnValues(AbuseReport abuseReport) {
        //TODO
        return new Object[0];
    }

    @Override
    public String getWhereClause(AbuseReport abuseReport) {
        //TODO
        return null;
    }

    @Override
    public AbuseReport getObjectFromResult(ResultSet result) {
        //TODO
        return null;
    }
}
