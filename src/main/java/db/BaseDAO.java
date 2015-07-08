package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDAO<T> {

    public abstract String getTableName();

    public abstract String[] getColumnValues(T t);
    public abstract String getWhereClause(T t);
    public abstract T getObjectFromResult(ResultSet result);

    public void insert(T object) {
        String[] values = getColumnValues(object);

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO ").append(getTableName()).append("(");
        for (int i = 0; i < values.length; i+=2) {
            if (i != 0) {
                sqlBuilder.append(",");
            }

            sqlBuilder.append(values[i]);
        }
        sqlBuilder.append(") VALUES (");
        for (int i = 1; i < values.length; i+=2) {
            if (i != 0) {
                sqlBuilder.append(",");
            }

            sqlBuilder.append(values[i]);
        }

        String sql = sqlBuilder.toString();
        try {
            Statement statement = DatabaseConnecter.getInstance().getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(T object) {
        String[] values = getColumnValues(object);
        String whereClause = getWhereClause(object);

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("UPDATE ").append(getTableName()).append(" SET ");
        for (int i = 0; i < values.length; i += 2) {
            if (i != 0) {
                sqlBuilder.append(",");
            }
            sqlBuilder.append(values[i]).append("='").append(values[i+1]).append("'");
        }
        sqlBuilder.append("WHERE ").append(whereClause);

        String sql = sqlBuilder.toString();
        try {
            Statement statement = DatabaseConnecter.getInstance().getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public T getObject(String column, String value) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM ")
                .append(getTableName())
                .append(" WHERE ")
                .append(column)
                .append("='")
                .append(value)
                .append("'");

        String sql = sqlBuilder.toString();

        ResultSet result = null;
        try {
            Statement statement = DatabaseConnecter.getInstance().getConnection().createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return getObjectFromResult(result);
    }

}
