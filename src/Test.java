import java.sql.*;

public class Test {
    public static void main(String[] args) {
        //getSQLData();
        // setSQLData();
        //closeConnectionStatement();
        preparedStatementTest("RODRİGO");
    }

    public static void getSQLData() {
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

    public static void setSQLData() {
        try {
            Connection postgres = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employee", "postgres", "000000");
            Statement statement = postgres.createStatement();
            //Veri çekmeden farkı itere edilebilir bir nesne olamayacağı için statement.execute metodunu kullanıyoruz. Ve içerisine veri ekleme kodlarını yazıyoruz.
            boolean execute = statement.execute("INSERT INTO t_manchester_city_rosters VALUES (16, 'RODRİGO', '', 50000, 'SPAIN', 1, 'ATLETICO MADRID')");
            System.out.println(execute);
            //Veri yüklenmiştir. boolean değer false dönerse veri başarıyla yüklenmiştir.
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void closeConnectionStatement1() {
        //Veri tabanı ile ilgili işlem yaptıktan sonra ilgili veri tabanını kapatmamız gerekiyor. Bunun için de Connection ve Statement sınıflarını kapatmamız lazım
        //1. çözüm try-with-resource ile otomatik kapamak. Connection ve Statement autoCloseable sınııfını implement eder.
        try (Connection postgres = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employee", "postgres", "000000");
             Statement statement = postgres.createStatement();
        ) {
            boolean execute = statement.execute("INSERT INTO t_manchester_city_rosters VALUES (10,'JACK', 'GREALISH', 10000, 'ENGLAND', 1, 'ASTON VILLA')");
            System.out.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnectionStatement2() {
        //Veri tabanı ile ilgili işlem yaptıktan sonra ilgili veri tabanını kapatmamız gerekiyor. Bunun için de Connection ve Statement sınıflarını kapatmamız lazım
        //2.Çözüm finally bloklarını kullanıp kapamak.
        Connection postgres = null;
        Statement statement = null;

        try {
            postgres = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employee", "postgres", "000000");
            statement = postgres.createStatement();
            boolean execute = statement.execute("INSERT INTO t_manchester_city_rosters VALUES (10,'JACK', 'GREALISH', 10000, 'ENGLAND', 1, 'ASTON VILLA')");
            System.out.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                postgres.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Yukarıdaki şekilde kapatabiliriz.
    }

    public static void preparedStatementTest(String id) {
        String queryById = "SELECT * FROM t_Manchester_City_Rosters WHERE person_name = ?";
        /*    91.satırda Statement yerine PreparedStatement kullanılması performans açısından önemlidir.
              PreparedStatement, sorgu derlemesini bir kez yaparak performans avantajı sağlar,
              Statement ise her çalıştırmada sorguyu yeniden derleyerek daha esnek olabilir
              ancak güvenlik ve performans açısından bazı dezavantajları vardır.
              Prepared Statement, Statement'e göre tercih edilir.
             */
        try (Connection connection = DBdatasource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(queryById);
        ) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String person_name = resultSet.getString("person_name");
                String jersey_number = resultSet.getString("jersey_number");
                String person_surname = resultSet.getString("person_surname");
                String from_team = resultSet.getString("from_team");
                System.out.println("Name:" + person_name + " Surname:" + person_surname + " Jersey:" + jersey_number + " From team:" + from_team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
