import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","1234");
    Statement st= con.createStatement();
       // System.out.println("Connection Success");

        /*
execute() methodu DDL(create, drop, alter table) ve DQL(select) için kullanılabilir.
1) Eğer execute() methodu DDL için kullanılırsa 'false' return yapar.
2) Eğer execute() methodu DQL için kullanılırsa ResultSet alındığında 'true' aksi hale 'false' verir.
 */
    //st.execute  sorguyu baslatio. database olusturdu
        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
     boolean sql1=   st.execute ("CREATE TABLE workers(worker_id VARCHAR(20),worker_name VARCHAR(20),worker_salary INT)");
        System.out.println("sql1 : " + sql1); //// DQL kullanmadan sadece tablo olusturduk bir data cagirmadik o yuzden false veriyor


        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.

        String sql2 = "ALTER TABLE workers ADD worker_address VARCHAR(80)";
        boolean sql2b = st.execute(sql2);
        System.out.println("sql2b = " + sql2b);

        //3.Örnek: Drop workers table

        String sql3= "drop table workers";
        st.execute(sql3);

       //4. adim
        con.close();
        st.close();

    }
}
