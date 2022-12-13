import javax.xml.transform.Result;
import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "1234");
        Statement st = con.createStatement();


        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.


       String sql1= "SELECT company, number_of_employees\n" +
               "FROM companies\n" +
               "ORDER BY number_of_employees DESC\n" +
               "OFFSET 1 ROW\n" +
               "FETCH NEXT 1 ROW ONLY" ;


     ResultSet resultSet1 = st.executeQuery(sql1);

     while(resultSet1.next()){  // datalari sirayla almak icin while loop
         System.out.println(resultSet1.getString("company") + "--"+ resultSet1.getInt("number_of_employees"));
       // number_of_employees int deger oldugu icin getint kullandik
     }


     //2.Yol Subquery Kullanarak

        String sql2="SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
                "                            FROM companies\n" +
                "                            WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
                "                                                         FROM companies))";


        ResultSet resultSet02=st.executeQuery(sql1); // donen datayi resulset e koyuyoruz

        while(resultSet02.next())// datalari while ile sirayla aliyoruz

        System.out.println(resultSet02.getString(1)+" --"+ resultSet02.getInt(2));

       con.close();
       st.close();
       resultSet1.close();
       resultSet02.close();




    }
}


