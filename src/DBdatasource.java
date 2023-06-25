import java.sql.*;

public class DBdatasource {

    public static final String DB_URL = "jdbc:postgresql://localhost:5432/Employee";
    public static final String USER = "postgres";
    public static final String PASSWORD = "000000";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
