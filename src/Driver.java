import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) {
        try {
            //DriverManager.getConnection metodu ile veri tabanına bağlanırız. Parametre olarak "jdbc:postgresql://" yazımı ile JDBC bağlantı protokolünü, localhost yazımı ile
            //yerel veritabanına yani kendi bilgisayarımdaki veri tabanına bağlandığımı, 5432 ile port numarası, Employee ile veritabanı adını yazdığımı belirtirim. Bunları
            //birleşik yazmalıyız.
            //"postgres" ile veri tabanı kullanıcı adını, "sifre1" ile de veri tabanı şifremi yazmalıyız.
            Connection postgres = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employee", "postgres", "sifre1");
            DatabaseMetaData metaData = postgres.getMetaData();
            System.out.println(metaData.getDriverName());
            System.out.println(metaData.getDriverVersion());
            System.out.println(metaData.getDefaultTransactionIsolation());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
