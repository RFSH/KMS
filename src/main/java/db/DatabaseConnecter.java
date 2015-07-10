package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnecter {
//    public final static String DB_URL = "jdbc:postgresql://localhost/kms";
//    public final static String DB_USER = "hadi";
//    public final static String DB_PASSWORD = "idunno";

    private static DatabaseConnecter instance;

    private Connection connection;

    private DatabaseConnecter() {
        Properties props = new Properties();
        props.setProperty("user", DatabaseConfig.DB_USER);
        props.setProperty("password",DatabaseConfig.DB_PASSWORD);
        try {
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnecter getInstance() {
        if (instance == null) {
            instance = new DatabaseConnecter();
        }
        return instance;
    }
}
