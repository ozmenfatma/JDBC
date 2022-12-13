import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {// burda trycatch ile handle ettik diger claslarda kullanmak icin
    private static Connection connection;
    private static Statement statement;


    //1. Adım: Driver'a kaydol
    //2. Adım: Database'e bağlan
    public static Connection connectToDataBase(String hostName,String dbName,String userName,String passWord)  {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dbName,userName,passWord);//parametrelendirdik
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection!=null){
            System.out.println("Connection success");
        }else{
            System.out.println("Connection fail");
        }

        return connection;
    }

    //3. Adım: Statement oluştur.
    public static Statement createStatement(){


        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return statement;
    }

    //4.adim : Query olustur --> ddl false dql true false boolean doner

    public static boolean execute(String sql){

        boolean isExecute;
        try {
           isExecute= statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isExecute;
    }
    //5. Adım: Bağlantı ve Statement'ı kapat.
    public static void closeConnectionAndStatement() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection.isClosed()&& statement.isClosed()){
                System.out.println("connection and statement closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}