import java.sql.Connection;
import java.sql.Statement;

public class Runner {

    public static void main(String[] args) {
        //1.Adim : Driver Kaydol
        //2. Adim: database baglan
         Connection connection= JdbcUtils.connectToDataBase("localhost","techproed","postgres","1234");

        //3.Adim :statement olustur
        Statement statement=JdbcUtils.createStatement();

        //4. Adim : Query calistir.
        JdbcUtils.execute("CREATE TABLE students (name VARCHAR(20), id INT, address VARCHAR(80))");
        System.out.println("Query executed");

        //5. Adım: Bağlantı ve Statement'ı kapat.

        JdbcUtils.closeConnectionAndStatement();
    }
}
