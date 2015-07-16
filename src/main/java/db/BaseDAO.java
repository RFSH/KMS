package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BaseDAO<T> {

    public abstract String getTableName();

    public abstract Object[] getColumnValues(T t);

    public abstract String getWhereClause(T t);

    public abstract T getObjectFromResult(ResultSet result);

    public void insert(T object, String table, Object[] values) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO ").append(table).append("(");
        for (int i = 0; i < values.length; i += 2) {
            if (i != 0) {
                sqlBuilder.append(",");
            }

            sqlBuilder.append(values[i]);
        }
        sqlBuilder.append(") VALUES (");
        for (int i = 1; i < values.length; i += 2) {
            if (i != 1) {
                sqlBuilder.append(",");
            }

            if (values[i] instanceof String) {
                sqlBuilder.append("'");
            }
            sqlBuilder.append(values[i]);
            if (values[i] instanceof String) {
                sqlBuilder.append("'");
            }
        }
        sqlBuilder.append(")");

        String sql = sqlBuilder.toString();
        System.out.println(sql);
        try {
            Statement statement = DatabaseConnecter.getInstance().getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(T object) {
        insert(object, getTableName(), getColumnValues(object));
    }

    public void update(T object, String table, String whereClause, Object[] values) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("UPDATE ").append(table).append(" SET ");
        for (int i = 0; i < values.length; i += 2) {
            if (i != 0) {
                sqlBuilder.append(",");
            }
            sqlBuilder.append(values[i]).append("=");
            if (values[i] instanceof String) {
                sqlBuilder.append("'");
            }
            sqlBuilder.append(values[i + 1]);
            if (values[i] instanceof String) {
                sqlBuilder.append("'");
            }
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

    public void update(T object) {
        update(object, getTableName(), getWhereClause(object), getColumnValues(object));
    }

    public ResultSet query(String table, String column, String value) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM ")
                .append(table);
        if (column != null && value != null) {
            sqlBuilder.append(" WHERE ")
                    .append(column)
                    .append("='")
                    .append(value)
                    .append("'");
        }

        String sql = sqlBuilder.toString();
        System.out.println(sql);
        ResultSet result = null;
        try {
            Statement statement = DatabaseConnecter.getInstance().getConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet query(String table, String queryStr) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM ")
                .append(table);
        if (queryStr != null) {
            sqlBuilder.append(" WHERE ")
                    .append(queryStr);
        }

        String sql = sqlBuilder.toString();
        System.out.println(sql);
        ResultSet result = null;
        try {
            Statement statement = DatabaseConnecter.getInstance().getConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public T getObject(String column, String value) {
        ResultSet result = query(getTableName(), column, value);
        try {
            if (!result.next()) {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return getObjectFromResult(result);
    }

    public List<T> getObjects(String column, String value) {
        List<T> list = new ArrayList<>();
        ResultSet result = query(getTableName(), column, value);

        try {
            while (result.next()) {
                list.add(getObjectFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<T> getObjects() {
        return getObjects(null, null);
    }

}
