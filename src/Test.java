import java.sql.*;

public class Test {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        try {
            Connection postgres = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employee", "postgres", "000000");
            Statement statement = postgres.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM t_Manchester_City_Rosters");
            while (resultSet.next()) {
                String person_name = resultSet.getString("person_name");
                String jersey_number = resultSet.getString("jersey_number");
                System.out.println(person_name);
                System.out.println(jersey_number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
