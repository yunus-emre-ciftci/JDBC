import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBdriver {
    public static final String getSQLData = "SELECT * FROM t_manchester_city_rosters";

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        try (Connection connection = DBdatasource.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getSQLData);
            while (resultSet.next()) {
                String surname = resultSet.getString("jersey_number");
                System.out.println(surname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
