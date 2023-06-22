import java.sql.*;

public class Test {
    public static void main(String[] args) {
        getSQLData();
        // setSQLData();
        //closeConnectionStatement();
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
            //Veri yüklenmiştir.
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
}
